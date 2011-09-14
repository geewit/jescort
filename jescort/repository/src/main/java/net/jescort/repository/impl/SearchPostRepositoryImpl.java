package net.jescort.repository.impl;

import net.jescort.domain.forum.Post;
import net.jescort.persistence.dao.PostDao;
import net.jescort.repository.SearchPostRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-23
 * Time: 下午12:52
 */
public class SearchPostRepositoryImpl implements SearchPostRepository
{
    protected transient final Log logger = LogFactory.getLog(this.getClass());

    private final String INDEXPATH = "k:\\index";
    private Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_33);
    private Directory ramDir;
    private Directory fsDir;

    @Resource(name = "postDao")
    private PostDao postDao;

    private void setup() throws IOException
    {
        ramDir = new RAMDirectory();
        File fsIndexDir = new File(INDEXPATH);
        fsDir = FSDirectory.open(fsIndexDir);
    }

    @Override
    public List<Post> searchPosts(String keywords)
    {
        try
        {
            List<Post> posts = new ArrayList<Post>();
            String fieldName = "title";
            IndexSearcher indexSearcher = new IndexSearcher(ramDir);

            //QueryParser parser = new QueryParser(fieldName, analyzer); //单 key 搜索
            //Query queryOBJ = parser.parse(query);
            System.out.println(">>> 2.开始读取索引... ... 通过关键字：【 " + keywords + " 】");

            //下面的是进行title,content 两个范围内进行收索.
            BooleanClause.Occur[] clauses = {BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD};
            Query queryOBJ = MultiFieldQueryParser.parse(Version.LUCENE_33, keywords, new String[]{"title", "content"}, clauses, analyzer);
            Filter filter = null;

            //################# 搜索相似度最高的记录 ###################
            TopDocs topDocs = indexSearcher.search(queryOBJ, filter, 1000);
            //TopDocs topDocs = indexSearcher.search(queryOBJ , 10000);
            System.out.println("*** 共匹配：" + topDocs.totalHits + "个 ***");

            Post post = null;

            //输出结果
            for(ScoreDoc scoreDoc : topDocs.scoreDocs)
            {
                Document targetDoc = indexSearcher.doc(scoreDoc.doc);
                post = new Post();
                Integer id = Integer.getInteger(targetDoc.get("id"), 0);
                post.setId(id);
                String content = targetDoc.get("content");
                post.setContent(content);
                posts.add(post);
            }

            indexSearcher.close();
            return posts;
        } catch (Exception e)
        {
            logger.warn(e.toString());
            return null;
        }
    }

    @Override
    public boolean createIndex()
    {
        if (this.isIndexExisted())
        {
            return this.isIndexExisted();
        }

        List<Post> posts = postDao.findAll();

        try
        {
            Directory directory = fsDir;
            IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_33, analyzer).setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND));

            for(Post post : posts)
            {
                Document doc = new Document();
                String content = StringUtils.defaultString(post.getContent());
                doc.add(new Field("content", content, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
                indexWriter.addDocument(doc);
            }
            indexWriter.optimize();
            indexWriter.close();
            return true;

        } catch (Exception e)
        {
            logger.warn(e.toString());
            return false;
        }
    }

    /**
     * check Index is Existed
     *
     * @return true or false
     */
    private boolean isIndexExisted()
    {
        try
        {
            File dir = new File(INDEXPATH);
            if (dir.listFiles().length > 0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (Exception e)
        {
            logger.warn(e.toString());
            return false;
        }
    }
}

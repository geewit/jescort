/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.search.operation;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import net.gelif.kernel.core.config.JescortConfig;
import net.gelif.kernel.core.util.Utilities;
import net.jescort.domain.forum.Post;
import net.jescort.search.FieldConstants;
import net.jescort.search.IndexManager;
import net.jescort.search.SearchHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.util.Version;

/**
 * This is the base class for all index operation.
 * These operations include:<br>
 * SearchOperation<br>
 * AddWeblogOperation<br>
 * RemoveWeblogOperation<br>
 * RebuildUserIndexOperation
 *
 * @author: admin@gelif.net
 */
public abstract class IndexOperation implements Runnable
{

    private static Log logger = LogFactory.getFactory().getInstance(IndexOperation.class);

    //~ Instance fields
    // ========================================================
    protected IndexManager manager;
    private IndexReader reader;
    private IndexWriter writer;

    //~ Constructors
    // ===========================================================
    public IndexOperation(IndexManager manager)
    {
        this.manager = manager;
    }

    //~ Methods
    // ================================================================
    protected Document getDocument(Post post)
    {

        // Actual comment content is indexed only if search.index.comments
        // is true or absent from the (static) configuration properties.
        // If false in the configuration, comments are treated as if empty.
        boolean indexComments = JescortConfig.getBooleanProperty("search.index.comments", true);

        String commentContent = "";
        String commentEmail = "";
        String commentName = "";
        if (indexComments)
        {
            List comments = post.getComments();
            if (comments != null)
            {
                StringBuffer commentEmailBuf = new StringBuffer();
                StringBuffer commentContentBuf = new StringBuffer();
                StringBuffer commentNameBuf = new StringBuffer();
                for(Iterator iterator = comments.iterator(); iterator.hasNext();)
                {
                    WeblogEntryComment comment = (WeblogEntryComment) iterator.next();
                    if (comment.getContent() != null)
                    {
                        commentContentBuf.append(comment.getContent());
                        commentContentBuf.append(",");
                    }
                    if (comment.getEmail() != null)
                    {
                        commentEmailBuf.append(comment.getEmail());
                        commentEmailBuf.append(",");
                    }
                    if (comment.getName() != null)
                    {
                        commentNameBuf.append(comment.getName());
                        commentNameBuf.append(",");
                    }
                }
                commentEmail = commentEmailBuf.toString();
                commentContent = commentContentBuf.toString();
                commentName = commentNameBuf.toString();
            }
        }

        Document doc = new Document();

        // keyword
        doc.add(new Field(FieldConstants.ID, post.getId().toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));

        // unindexed
        doc.add(new Field(FieldConstants.ANCHOR,
                post.getAnchor(),
                Field.Store.YES, Field.Index.NO));

        // text
        doc.add(new Field(FieldConstants.USERNAME,
                post.getPoster().getUsername(),
                Field.Store.YES, Field.Index.ANALYZED));

        // index the entry text, but don't store it - moved to end of block
        // unstored
        doc.add(new Field(FieldConstants.CONTENT,
                post.getText(),
                Field.Store.NO, Field.Index.ANALYZED));

        // store an abbreviated version of the entry text, but don't index
        // unindexed
        doc.add(new Field(FieldConstants.CONTENT_STORED,
                Utilities.truncateNicely(Utilities.removeHTML(post.getContent()), 240, 260, "..."),
                Field.Store.YES, Field.Index.NO));

        // keyword
        doc.add(new Field(FieldConstants.UPDATED,
                post.getEdit().getEditdate().toString(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));

        // keyword
        doc.add(new Field(FieldConstants.PUBLISHED,
                post.getCreatedate().toString(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));

        // index Comments
        // unstored
        doc.add(new Field(FieldConstants.C_CONTENT,
                commentContent,
                Field.Store.NO, Field.Index.ANALYZED));
        // unstored
        doc.add(new Field(FieldConstants.C_EMAIL,
                commentEmail,
                Field.Store.NO, Field.Index.ANALYZED));
        // unstored
        doc.add(new Field(FieldConstants.C_NAME,
                commentName,
                Field.Store.NO, Field.Index.ANALYZED));

        // unstored
        doc.add(new Field(FieldConstants.CONSTANT,
                FieldConstants.CONSTANT_V,
                Field.Store.NO, Field.Index.ANALYZED));

        return doc;
    }

    protected IndexReader beginDeleting()
    {
        try
        {
            reader = IndexReader.open(manager.getIndexDirectory());
        } catch (IOException e)
        {
        }

        return reader;
    }

    protected void endDeleting()
    {
        if (reader != null)
        {
            try
            {
                reader.close();
            } catch (IOException e)
            {
                logger.error("ERROR closing reader");
            }
        }
    }

    protected IndexWriter beginWriting()
    {
        try
        {
            Analyzer analyzer = SearchHelper.getAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_34, analyzer);
            writer = new IndexWriter(manager.getIndexDirectory(), indexWriterConfig);
        } catch (IOException e)
        {
            logger.error("ERROR creating writer", e);
        }

        return writer;
    }

    protected void endWriting()
    {
        if (writer != null)
        {
            try
            {
                writer.close();
            } catch (IOException e)
            {
                logger.error("ERROR closing writer", e);
            }
        }
    }

    public void run()
    {
        doRun();
    }

    protected abstract void doRun();
}

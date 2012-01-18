package net.jescort.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.util.Version;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-9-17
 * Time: 下午6:14
 */
public class SearchHelper
{
    /**
     * This is the analyzer that will be used to tokenize comment text.
     *
     * @return Analyzer to be used in manipulating the database.
     */
    public static final Analyzer getAnalyzer()
    {
        return new SmartChineseAnalyzer(Version.LUCENE_34);
    }
}

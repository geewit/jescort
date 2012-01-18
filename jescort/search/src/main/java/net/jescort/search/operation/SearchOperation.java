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

import net.jescort.search.FieldConstants;
import net.jescort.search.IndexManager;
import net.jescort.search.IndexManagerImpl;
import net.jescort.search.IndexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;


/**
 * An operation that searches the index.
 *
 * @author: admin@gelif.net
 */
public class SearchOperation extends ReadFromIndexOperation
{

    //~ Static fields/initializers =============================================

    private static Log logger = LogFactory.getFactory().getInstance(SearchOperation.class);

    private static String[] SEARCH_FIELDS = new String[]{
            FieldConstants.CONTENT,
            FieldConstants.TITLE,
            FieldConstants.C_CONTENT,
            FieldConstants.CATEGORY
    };

    private static BooleanClause.Occur[] SEARCH_FLAGS = new BooleanClause.Occur[]{
            BooleanClause.Occur.SHOULD,
            BooleanClause.Occur.SHOULD,
            BooleanClause.Occur.SHOULD,
            BooleanClause.Occur.SHOULD
    };

    private static Sort SORTER = new Sort(new SortField(FieldConstants.PUBLISHED, SortField.STRING, true));

    //~ Instance fields ========================================================

    private String term;
    private String websiteHandle;
    private String category;
    private TopFieldDocs searchResults;
    private String parseError;

    //~ Constructors ===========================================================

    /**
     * Create a new operation that searches the index.
     */
    public SearchOperation(IndexManager mgr)
    {
        // TODO: finish moving  IndexManager to backend, so this cast is not needed
        super(mgr);
    }

    //~ Methods ================================================================

    public void setTerm(String term)
    {
        this.term = term;
    }

    /* (non-Javadoc)
    * @see java.lang.Runnable#run()
    */
    public void doRun()
    {
        searchResults = null;

        IndexSearcher searcher = null;

        try
        {
            IndexReader reader = manager.getSharedIndexReader();
            searcher = new IndexSearcher(reader);

            Query query = MultiFieldQueryParser.parse(Version.LUCENE_34, term, SEARCH_FIELDS, SEARCH_FLAGS, new StandardAnalyzer(Version.LUCENE_CURRENT));

            Term tUsername = IndexUtil.getTerm(FieldConstants.WEBSITE_HANDLE, websiteHandle);

            if (tUsername != null)
            {
                BooleanQuery bQuery = new BooleanQuery();
                bQuery.add(query, BooleanClause.Occur.MUST);
                bQuery.add(new TermQuery(tUsername), BooleanClause.Occur.MUST);
                query = bQuery;
            }

            Term tCategory = IndexUtil.getTerm(FieldConstants.CATEGORY, category);

            if (tCategory != null)
            {
                BooleanQuery bQuery = new BooleanQuery();
                bQuery.add(query, BooleanClause.Occur.MUST);
                bQuery.add(new TermQuery(tCategory), BooleanClause.Occur.MUST);
                query = bQuery;
            }
            searchResults = searcher.search(query, null/*Filter*/, 10, SORTER);

        } catch (IOException e)
        {
            logger.error("Error searching index", e);
            parseError = e.getMessage();

        } catch (ParseException e)
        {
            // who cares?
            parseError = e.getMessage();
        }
        // don't need to close the reader, since we didn't do any writing!
    }

    public TopFieldDocs getResults()
    {
        return searchResults;
    }

    public int getTotalHits()
    {
        if (searchResults == null)
        {
            return -1;
        }

        return searchResults.totalHits;
    }

    public String getParseError()
    {
        return parseError;
    }

    /**
     * @param websiteHandle
     */
    public void setWebsiteHandle(String websiteHandle)
    {
        this.websiteHandle = websiteHandle;
    }

    /**
     * @param category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

}

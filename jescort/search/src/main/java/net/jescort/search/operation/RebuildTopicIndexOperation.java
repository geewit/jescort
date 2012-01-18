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
import java.text.MessageFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.gelif.kernel.core.exception.JescortException;
import net.jescort.domain.forum.Post;
import net.jescort.domain.forum.Topic;
import net.jescort.search.FieldConstants;
import net.jescort.search.IndexManager;
import net.jescort.search.IndexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

/**
 * An index operation that rebuilds a given users index (or all indexes).
 *
 * @author: admin@gelif.net
 */
public class RebuildTopicIndexOperation extends WriteToIndexOperation
{

    //~ Static fields/initializers =============================================

    private static Log logger = LogFactory.getFactory().getInstance(RebuildTopicIndexOperation.class);

    //~ Instance fields ========================================================

    private Topic topic;

    //~ Constructors ===========================================================

    /**
     * Create a new operation that will recreate an index.
     *
     * @param topic The topic to rebuild the index for, or null for all users.
     */
    public RebuildTopicIndexOperation(IndexManager manager, Topic topic)
    {
        super(manager);
        this.topic = topic;
    }

    //~ Methods ================================================================

    public void doRun()
    {
        Date start = new Date();

        // since this operation can be run on a separate thread we must treat
        // the weblog object passed in as a detached object which is proned to
        // lazy initialization problems, so requery for the object now
        if (this.topic != null)
        {
            logger.debug("Reindexining weblog " + topic.getHandle());
            try
            {
                this.topic = roller.getWeblogManager().getWeblog(this.topic.getId());
            } catch (JescortException ex)
            {
                logger.error("Error getting topic object", ex);
                return;
            }
        } else
        {
            logger.debug("Reindexining entire site");
        }

        IndexReader reader = beginDeleting();

        try
        {
            if (reader != null)
            {
                Term tWebsite = null;
                if (topic != null)
                {
                    tWebsite = IndexUtil.getTerm(FieldConstants.WEBSITE_HANDLE, topic.getHandle());
                }
                if (tWebsite != null)
                {
                    reader.deleteDocuments(tWebsite);
                } else
                {
                    Term all = IndexUtil.getTerm(FieldConstants.CONSTANT,FieldConstants.CONSTANT_V);
                    reader.deleteDocuments(all);
                }
            }
        } catch (IOException e)
        {
            logger.info("Problems deleting doc from index", e);
        } finally
        {
            endDeleting();
        }

        IndexWriter writer = beginWriting();

        try
        {
            if (writer != null)
            {
                WeblogEntryManager weblogManager = roller.getWeblogEntryManager();
                List entries = weblogManager.getWeblogEntries(
                        topic,                   // topic
                        null,
                        null,                      // startDate
                        null,                      // endDate
                        null,                      // catName
                        null, WeblogEntry.PUBLISHED, // status
                        null,                      // text
                        null,                      // sortby (null means pubTime)
                        null,
                        null,
                        0, -1);     // offset, length, locale
                logger.debug("Entries to index: " + entries.size());
                for(Iterator wbItr = entries.iterator(); wbItr.hasNext(); )
                {
                    Post post = (Post) wbItr.next();
                    writer.addDocument(getDocument(post));
                    logger.debug(
                            MessageFormat.format("Indexed entry {0}: {1}",
                                    new Object[]{post.getPubTime(), post.getAnchor()}));
                }
            }
        } catch (Exception e)
        {
            logger.error("ERROR adding doc to index", e);
        } finally
        {
            endWriting();
        }

        Date end = new Date();
        double length = (end.getTime() - start.getTime()) / (double) 1000;

        if (topic == null)
        {
            logger.info(
                    "Completed rebuilding index for all users in '" + length + "' secs");
        } else
        {
            logger.info("Completed rebuilding index for topic handle: '" + topic.getHandle() + "' in '" + length + "' seconds");
        }
    }
}

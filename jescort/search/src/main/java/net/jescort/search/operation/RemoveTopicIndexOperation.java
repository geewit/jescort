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
import java.util.Date;

import net.gelif.kernel.core.exception.JescortException;
import net.jescort.domain.forum.Topic;
import net.jescort.search.FieldConstants;
import net.jescort.search.IndexManager;
import net.jescort.search.IndexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;


/**
 * An index operation that rebuilds a given users index (or all indexes).
 *
 * @author: admin@gelif.net
 */
public class RemoveTopicIndexOperation extends WriteToIndexOperation
{

    //~ Static fields/initializers =============================================

    private static Log logger = LogFactory.getFactory().getInstance(RemoveTopicIndexOperation.class);

    //~ Instance fields ========================================================

    private Topic topic;

    //~ Constructors ===========================================================

    /**
     * Create a new operation that will recreate an index.
     *
     * @param topic The topic to rebuild the index for, or null for all sites.
     */
    public RemoveTopicIndexOperation(IndexManager manager, Topic topic)
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
        try
        {
            this.topic = roller.getWeblogManager().getWeblog(this.topic.getId());
        } catch (JescortException ex)
        {
            logger.error("Error getting topic object", ex);
            return;
        }

        IndexReader reader = beginDeleting();
        try
        {
            if (reader != null)
            {
                String handle = null;
                if (topic != null)
                {
                    handle = topic.getHandle();
                }
                Term tHandle = IndexUtil.getTerm(FieldConstants.WEBSITE_HANDLE, handle);

                if (tHandle != null)
                {
                    reader.deleteDocuments(tHandle);
                }
            }
        } catch (IOException e)
        {
            logger.info("Problems deleting doc from index", e);
        } finally
        {
            endDeleting();
        }

        Date end = new Date();
        double length = (end.getTime() - start.getTime()) / (double) 1000;

        if (topic != null)
        {
            logger.info("Completed deleting indices for topic '" + topic.getName() + "' in '" + length + "' seconds");
        }
    }
}

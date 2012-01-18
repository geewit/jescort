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

import net.gelif.kernel.core.exception.JescortException;
import net.jescort.domain.forum.Post;
import net.jescort.search.IndexManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.index.IndexWriter;

/**
 * An operation that adds a new log entry into the index.
 * @author: admin@gelif.net
 */
public class AddPostOperation extends WriteToIndexOperation
{
    //~ Static fields/initializers =============================================

    private static Log logger = LogFactory.getFactory().getInstance(AddPostOperation.class);

    //~ Instance fields ========================================================

    private Post post;

    //~ Constructors ===========================================================

    /**
     * Adds a web log entry into the index.
     */
    public AddPostOperation(IndexManager manager, Post post)
    {
        super(manager);
        this.post = post;
    }

    //~ Methods ================================================================

    public void doRun()
    {
        IndexWriter writer = beginWriting();

        // since this operation can be run on a separate thread we must treat
        // the weblog object passed in as a detached object which is proned to
        // lazy initialization problems, so requery for the object now
        try
        {
            this.post = wMgr.getWeblogEntry(this.post.getId());
        } catch (JescortException ex)
        {
            logger.error("Error getting weblogentry object", ex);
            return;
        }

        try
        {
            if (writer != null)
            {
                writer.addDocument(getDocument(post));
            }
        } catch (IOException e)
        {
            logger.error("Problems adding doc to index", e);
        } finally
        {
            endWriting();
        }
    }
}

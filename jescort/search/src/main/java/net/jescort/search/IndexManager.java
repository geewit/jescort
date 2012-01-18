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
package net.jescort.search;


import net.gelif.kernel.core.exception.JescortException;
import net.gelif.kernel.core.exception.SearchException;
import net.jescort.domain.forum.Post;
import net.jescort.domain.forum.Topic;
import net.jescort.search.operation.IndexOperation;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Interface to Jescort's Lucene-based search facility.
 *
 * @author: admin@gelif.net
 */
public interface IndexManager
{
    public Directory getIndexDirectory();

    public ReadWriteLock getReadWriteLock();

    public void resetSharedReader();

    public IndexReader getSharedIndexReader();

    /**
     * Does index need to be rebuild
     */
    public abstract boolean isInconsistentAtStartup();

    /**
     * Remove topic from index, returns immediately and operates in background
     */
    public void removeTopicIndex(Topic topic) throws JescortException;

    /**
     * Remove post from index, returns immediately and operates in background
     */
    public void removePostIndexOperation(Post post) throws JescortException;

    /**
     * Add post to index, returns immediately and operates in background
     */
    public void addPostIndexOperation(Post post) throws JescortException;

    /**
     * R-index post, returns immediately and operates in background
     */
    public void addPostReIndexOperation(Post post) throws JescortException;

    /**
     * Execute operation immediately
     */
    public abstract void executeIndexOperationNow(final IndexOperation operation);

    /**
     * Release all resources associated with Roller session.
     */
    public abstract void release();


    /**
     * Initialize the search system.
     *
     * @throws net.gelif.kernel.core.exception.SearchException If there is a problem during initialization.
     */
    public void initialize() throws SearchException;


    /**
     * Shutdown to be called on application shutdown
     */
    public abstract void shutdown();

    public abstract void rebuildTopicIndex(Topic topic) throws JescortException;

    public abstract void rebuildTopicIndex() throws JescortException;

}

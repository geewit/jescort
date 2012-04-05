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

import java.io.File;
import java.io.IOException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.gelif.kernel.core.config.JescortConfig;
import net.gelif.kernel.core.exception.JescortException;
import net.gelif.kernel.core.exception.SearchException;
import net.jescort.domain.forum.Post;
import net.jescort.domain.forum.Topic;
import net.jescort.search.operation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;


/**
 * Lucene implementation of IndexManager. This is the central entry point
 * into the Lucene searching API.
 * @author: admin@gelif.net
 */
public class IndexManagerImpl implements IndexManager
{
    //~ Static fields/initializers
    // =============================================

    private IndexReader reader;

    static Log logger = LogFactory.getFactory().getInstance(IndexManagerImpl.class);

    //~ Instance fields
    // ========================================================

    private boolean searchEnabled = true;

    File indexConsistencyMarker;

    private boolean useRAMIndex = false;

    private RAMDirectory fRAMindex;

    private String indexDir = null;

    private boolean inconsistentAtStartup = false;

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    //~ Constructors
    // ===========================================================

    /**
     * Creates a new lucene index manager. This should only be created once.
     * Creating the index manager more than once will definately result in
     * errors. The preferred way of getting an index is through the
     * RollerContext.
     */
    protected IndexManagerImpl()
    {
        // check config to see if the internal search is enabled
        String enabled = JescortConfig.getProperty("search.enabled");
        if ("false".equalsIgnoreCase(enabled))
        {
            this.searchEnabled = false;
        }

        // we also need to know what our index directory is
        // Note: system property expansion is now handled by WebloggerConfig
        String searchIndexDir = JescortConfig.getProperty("search.index.dir");
        this.indexDir = searchIndexDir.replace('/', File.separatorChar);

        // a little debugging
        logger.info("search enabled: " + this.searchEnabled);
        logger.info("index dir: " + this.indexDir);

        String test = indexDir + File.separator + ".index-inconsistent";
        indexConsistencyMarker = new File(test);
    }


    /**
     * @inheritDoc
     */
    public void initialize() throws SearchException
    {
        // only initialize the index if search is enabled
        if (this.searchEnabled)
        {

            // 1. If inconsistency marker exists.
            //     Delete index
            // 2. if we're using RAM index
            //     load ram index wrapper around index
            //
            if (indexConsistencyMarker.exists())
            {
                getFSDirectory();
                inconsistentAtStartup = true;
                logger.debug("Index inconsistent: marker exists");
            } else
            {
                try
                {
                    File makeIndexDir = new File(indexDir);
                    if (!makeIndexDir.exists())
                    {
                        makeIndexDir.mkdirs();
                        inconsistentAtStartup = true;
                        logger.debug("Index inconsistent: new");
                    }
                    indexConsistencyMarker.createNewFile();
                } catch (IOException e)
                {
                    logger.error(e);
                }
            }

            if (indexExists())
            {
                if (useRAMIndex)
                {
                    Directory filesystem = getFSDirectory();
                    try
                    {
                        fRAMindex = new RAMDirectory(filesystem);
                    } catch (IOException e)
                    {
                        logger.error("Error creating in-memory index", e);
                    }
                }
            } else
            {
                logger.debug("Creating index");
                inconsistentAtStartup = true;
                if (useRAMIndex)
                {
                    fRAMindex = new RAMDirectory();
                    createIndex(fRAMindex);
                } else
                {
                    createIndex(getFSDirectory());
                }
            }

            if (isInconsistentAtStartup())
            {
                logger.info("Index was inconsistent. Rebuilding index in the background...");
                try
                {
                    rebuildTopicIndex();
                } catch (JescortException e)
                {
                    logger.error("ERROR: scheduling re-index operation");
                }
            } else
            {
                logger.info("Index initialized and ready for use.");
            }
        }

    }

    //~ Methods
    // ================================================================

    public void rebuildTopicIndex() throws JescortException
    {
        scheduleIndexOperation(new RebuildTopicIndexOperation(this, null));
    }

    public void rebuildTopicIndex(Topic topic) throws JescortException
    {
        scheduleIndexOperation(new RebuildTopicIndexOperation(this, topic));
    }

    public void removeTopicIndex(Topic topic) throws JescortException
    {
        scheduleIndexOperation(new RemoveTopicIndexOperation(this, topic));
    }

    public void addPostIndexOperation(Post post) throws JescortException
    {
        AddPostOperation addPostOperation = new AddPostOperation(this, post);
        scheduleIndexOperation(addPostOperation);
    }

    public void addPostReIndexOperation(Post post) throws JescortException
    {
        ReIndexPostOperation reindex = new ReIndexPostOperation(this, post);
        scheduleIndexOperation(reindex);
    }

    public void removePostIndexOperation(Post post) throws JescortException
    {
        RemovePostOperation removePostOperation = new RemovePostOperation(this, post);
        executeIndexOperationNow(removePostOperation);
    }

    public ReadWriteLock getReadWriteLock()
    {
        return rwl;
    }

    public boolean isInconsistentAtStartup()
    {
        return inconsistentAtStartup;
    }

    private void scheduleIndexOperation(final IndexOperation operation)
    {
        // only if search is enabled
        if (this.searchEnabled)
        {
            logger.debug("Starting scheduled index operation: " + operation.getClass().getName());
        }
    }

    public void executeIndexOperationNow(final IndexOperation operation)
    {
        // only if search is enabled
        if (this.searchEnabled)
        {
            logger.debug("Executing index operation now: " + operation.getClass().getName());
        }
    }

    public synchronized void resetSharedReader()
    {
        reader = null;
    }

    public synchronized IndexReader getSharedIndexReader()
    {
        if (reader == null)
        {
            try
            {
                reader = IndexReader.open(getIndexDirectory());
            } catch (IOException e)
            {
            }
        }
        return reader;
    }

    /**
     * Get the directory that is used by the lucene index. This method will
     * return null if there is no index at the directory location. If we are
     * using a RAM index, the directory will be a ram directory.
     *
     * @return Directory The directory containing the index, or null if error.
     */
    public Directory getIndexDirectory()
    {
        if (useRAMIndex)
        {
            return fRAMindex;
        } else
        {
            return getFSDirectory();
        }
    }

    private boolean indexExists()
    {
        try
        {
            return IndexReader.indexExists(FSDirectory.open(new File(indexDir)));
        } catch (IOException e)
        {
            logger.error("Problem accessing index directory", e);
            return false;
        }
    }

    Directory getFSDirectory()
    {
        Directory directory = null;

        try
        {
            directory = FSDirectory.open(new File(indexDir));
        } catch (IOException e)
        {
            logger.error("Problem accessing index directory", e);
        }

        return directory;
    }

    private void createIndex(Directory dir)
    {
        IndexWriter writer = null;

        try
        {
            Analyzer analyzer = SearchHelper.getAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_34, analyzer);
            writer = new IndexWriter(dir, indexWriterConfig);
        } catch (IOException e)
        {
            logger.error("Error creating index", e);
        } finally
        {
            try
            {
                if (writer != null)
                {
                    writer.close();
                }
            } catch (IOException e)
            {
            }
        }
    }

    private IndexOperation getSaveIndexOperation()
    {
        return new WriteToIndexOperation(this)
        {
            public void doRun()
            {
                Directory dir = getIndexDirectory();
                Directory fsdir = getFSDirectory();

                IndexWriter writer = null;

                try
                {
                    Analyzer analyzer = SearchHelper.getAnalyzer();
                    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_34, analyzer);
                    writer = new IndexWriter(fsdir, indexWriterConfig);
                    writer.addIndexes(dir);
                    indexConsistencyMarker.delete();
                } catch (IOException e)
                {
                    logger.error("Problem saving index to disk", e);

                    // Delete the directory, since there was a problem saving
                    // the RAM contents
                    getFSDirectory();
                } finally
                {
                    try
                    {
                        if (writer != null)
                        {
                            writer.close();
                        }
                    } catch (IOException e1)
                    {
                        logger.warn("Unable to close IndexWriter.");
                    }
                }

            }
        };
    }

    public void release()
    {
        // no-op
    }

    public void shutdown()
    {
        if (useRAMIndex)
        {
            scheduleIndexOperation(getSaveIndexOperation());
        } else
        {
            indexConsistencyMarker.delete();
        }

        try
        {
            if (reader != null)
            {
                reader.close();
            }
        } catch (IOException e)
        {
            // won't happen, since it was
        }
    }

}

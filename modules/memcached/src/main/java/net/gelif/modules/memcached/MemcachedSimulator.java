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
package net.gelif.modules.memcached;

import net.spy.memcached.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.thimbleware.jmemcached.CacheImpl;
import com.thimbleware.jmemcached.Key;
import com.thimbleware.jmemcached.LocalCacheElement;
import com.thimbleware.jmemcached.MemCacheDaemon;
import com.thimbleware.jmemcached.storage.CacheStorage;
import com.thimbleware.jmemcached.storage.hash.ConcurrentLinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-12
 * Time: 上午12:11
 */
public class MemcachedSimulator implements InitializingBean, DisposableBean
{
    private static Logger logger = LoggerFactory.getLogger(MemcachedSimulator.class);

    private MemCacheDaemon<LocalCacheElement> jmemcached;

    private String serverUrl = "localhost:11211";

    private int maxItems = 1024 * 100;
    private long maxBytes = 1024 * 100 * 2048;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        logger.info("Initializing JMemcached Server");

        jmemcached = new MemCacheDaemon<LocalCacheElement>();

        CacheStorage<Key, LocalCacheElement> storage = ConcurrentLinkedHashMap.create(
                ConcurrentLinkedHashMap.EvictionPolicy.FIFO, maxItems, maxBytes);
        jmemcached.setCache(new CacheImpl(storage));

        jmemcached.setAddr(AddrUtil.getAddresses(serverUrl).get(0));

        jmemcached.start();

        logger.info("Initialized JMemcached Server");
    }

    @Override
    public void destroy() throws Exception
    {
        logger.info("Shutdowning Jmemcached Server");
        jmemcached.stop();
        logger.info("Shutdowned Jmemcached Server");
    }

    public void setServerUrl(String serverUrl)
    {
        this.serverUrl = serverUrl;
    }

    public void setMaxItems(int maxItems)
    {
        this.maxItems = maxItems;
    }

    public void setMaxBytes(long maxBytes)
    {
        this.maxBytes = maxBytes;
    }
}
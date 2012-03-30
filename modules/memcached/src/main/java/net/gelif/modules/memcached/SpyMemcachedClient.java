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

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-10
 * Time: 上午9:53
 */

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.MemcachedClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

public class SpyMemcachedClient implements DisposableBean
{
    private static Logger logger = LoggerFactory.getLogger(SpyMemcachedClient.class);

    private MemcachedClient memcachedClient;

    private long shutdownTimeout = 1000;

    /**
     * Get方法, 转换结果类型并屏蔽异常, 仅返回Null.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key)
    {
        try
        {
            return (T) memcachedClient.get(key);
        } catch (RuntimeException e)
        {
            handleException(e, key);
            return null;
        }
    }

    /**
     * GetBulk方法, 转换结果类型并屏蔽异常.
     */
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> getBulk(Collection<String> keys)
    {
        try
        {
            return (Map<String, T>) memcachedClient.getBulk(keys);
        } catch (RuntimeException e)
        {
            handleException(e, StringUtils.join(keys, ","));
            return null;
        }
    }

    /**
     * 异步Set方法, 不考虑执行结果.
     */
    public void set(String key, int expiredTime, Object value)
    {
        memcachedClient.set(key, expiredTime, value);
    }

    /**
     * 安全的Set方法,在1秒内返回结果, 否则返回false并取消操作.
     */
    public boolean safeSet(String key, Object value, int expiration)
    {
        Future<Boolean> future = memcachedClient.set(key, expiration, value);
        try
        {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e)
        {
            future.cancel(false);
        }
        return false;
    }

    /**
     * 异步 Delete方法, 不考虑执行结果.
     */
    public void delete(String key)
    {
        memcachedClient.delete(key);
    }

    /**
     * 安全的Set方法,在1秒内返回结果, 否则返回false并取消操作.
     */
    public boolean safeDelete(String key)
    {
        Future<Boolean> future = memcachedClient.delete(key);
        try
        {
            return future.get(1, TimeUnit.SECONDS);
        } catch (Exception e)
        {
            future.cancel(false);
        }
        return false;
    }

    /**
     * Incr方法.
     */
    public long incr(String key, int by, long defaultValue)
    {
        return memcachedClient.incr(key, by, defaultValue);
    }

    /**
     * Decr方法.
     */
    public long decr(String key, int by, long defaultValue)
    {
        return memcachedClient.decr(key, by, defaultValue);
    }

    /**
     * 异步Incr方法, 不支持默认值, 若key不存在返回-1.
     */
    public Future<Long> asyncIncr(String key, int by)
    {
        return memcachedClient.asyncIncr(key, by);
    }

    /**
     * 异步Decr方法, 不支持默认值, 若key不存在返回-1.
     */
    public Future<Long> asyncDecr(String key, int by)
    {
        return memcachedClient.asyncDecr(key, by);
    }

    private RuntimeException handleException(Exception e, String key)
    {
        logger.warn("spymemcached client receive an exception with key:" + key, e);
        return new RuntimeException(e);
    }

    @Override
    public void destroy() throws Exception
    {
        if (memcachedClient != null)
        {
            memcachedClient.shutdown(shutdownTimeout, TimeUnit.MILLISECONDS);
        }
    }

    public MemcachedClient getMemcachedClient()
    {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient)
    {
        this.memcachedClient = memcachedClient;
    }

    public void setShutdownTimeout(long shutdownTimeout)
    {
        this.shutdownTimeout = shutdownTimeout;
    }
}
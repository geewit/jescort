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
package net.gelif.kernel.core.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * AsyncToken工具类方法,用executor执行任务并将执行结果设置进AsyncToken并返回
 *
 * @author admin@gelif.net
 */
public class AsyncTokenUtils
{
    /**
     * 通过executor执行Callable,将callable的执行结果设置进token
     */
    public static <T> void execute(Executor executor, AsyncToken<T> token, final Callable<T> task)
    {
        executor.execute(new AsyncTokenRunnable<T>(token, task));
    }

    /**
     * 通过executor执行Runnable
     */
    public static <T> void execute(Executor executor, AsyncToken<T> token, final Runnable task)
    {
        executor.execute(new AsyncTokenRunnable<T>(token, task));
    }

    /**
     * 通过executor执行Callable,将callable的执行结果设置进token
     */
    public static <T> AsyncToken<T> execute(Executor executor, AsyncTokenFactory<T> factory, final Callable<T> task)
    {
        AsyncToken<T> token = factory.newToken();
        executor.execute(new AsyncTokenRunnable<T>(token, task));
        return token;
    }

    /**
     * 通过executor执行Runnable
     */
    public static <T> AsyncToken<T> execute(Executor executor, AsyncTokenFactory<T> factory, final Runnable task)
    {
        AsyncToken<T> token = factory.newToken();
        executor.execute(new AsyncTokenRunnable<T>(token, task));
        return token;
    }

}

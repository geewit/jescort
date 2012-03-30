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

/**
 * Runnable及Callable的代理类,用于代理执行Runnable.run()及Callable.call()方法,并将执行结果设置进token
 *
 * @author admin@gelif.net
 * @see AsyncTokenUtils
 */
public class AsyncTokenRunnable<T> implements Runnable
{
    AsyncToken<T> asyncToken;
    Runnable targetRunnable;
    Callable<T> targetCallable;

    public AsyncTokenRunnable(AsyncToken<T> asyncToken, Runnable target)
    {
        if (asyncToken == null)
        {
            throw new IllegalArgumentException("asyncToken must be not null");
        }
        if (target == null)
        {
            throw new IllegalArgumentException("target Runnable must be not null");
        }
        this.asyncToken = asyncToken;
        this.targetRunnable = target;
    }

    public AsyncTokenRunnable(AsyncToken<T> asyncToken, Callable<T> target)
    {
        if (asyncToken == null)
        {
            throw new IllegalArgumentException("asyncToken must be not null");
        }
        if (target == null)
        {
            throw new IllegalArgumentException("target Callable must be not null");
        }
        this.asyncToken = asyncToken;
        this.targetCallable = target;
    }

    public AsyncToken<T> getAsyncToken()
    {
        return asyncToken;
    }

    public void run()
    {
        try
        {
            if (targetRunnable == null)
            {
                asyncToken.setComplete(targetCallable.call());
            } else
            {
                targetRunnable.run();
                asyncToken.setComplete();
            }
        } catch (Exception e)
        {
            asyncToken.setFault(e);
        }
    }

}
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
package net.gelif.modules.web.session.wrapper;

import java.util.Collections;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * Created by IntelliJ IDEA. User: admin@gelif.net Date: 11-7-16 Time: 下午2:59
 */

@SuppressWarnings("deprecation")
public class HttpSessionWrapper implements HttpSession
{
    HttpSession delegate;

    public HttpSessionWrapper(HttpSession session)
    {
        super();
        this.delegate = session;
    }

    public Object getAttribute(String key)
    {
        return delegate.getAttribute(key);
    }

    public Enumeration<String> getAttributeNames()
    {
        return delegate.getAttributeNames();
    }

    public long getCreationTime()
    {
        return delegate.getCreationTime();
    }

    public String getId()
    {
        return delegate.getId();
    }

    public long getLastAccessedTime()
    {
        return delegate.getLastAccessedTime();
    }

    public int getMaxInactiveInterval()
    {
        return delegate.getMaxInactiveInterval();
    }

    public ServletContext getServletContext()
    {
        return delegate.getServletContext();
    }

    public HttpSessionContext getSessionContext()
    {
        return delegate.getSessionContext();
    }

    public Object getValue(String key)
    {
        return getAttribute(key);
    }

    public String[] getValueNames()
    {
        return Collections.list(getAttributeNames()).toArray(new String[]{});
    }

    public void invalidate()
    {
        delegate.invalidate();
    }

    public boolean isNew()
    {
        return delegate.isNew();
    }

    public void putValue(String key, Object value)
    {
        setAttribute(key, value);
    }

    public void removeAttribute(String key)
    {
        delegate.removeAttribute(key);
    }

    public void removeValue(String key)
    {
        removeAttribute(key);
    }

    public void setAttribute(String key, Object v)
    {
        delegate.setAttribute(key, v);
    }

    public void setMaxInactiveInterval(int v)
    {
        delegate.setMaxInactiveInterval(v);
    }
}

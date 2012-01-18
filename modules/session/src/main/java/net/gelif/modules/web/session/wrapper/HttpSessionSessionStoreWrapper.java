package net.gelif.modules.web.session.wrapper;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-16
 * Time: 下午2:58
 */

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpSession;

import net.gelif.modules.web.session.store.SessionStore;

public class HttpSessionSessionStoreWrapper extends HttpSessionWrapper
{
    String sessionId;
    Map<String, Object> sessionData;
    SessionStore store;

    public HttpSessionSessionStoreWrapper(HttpSession session, SessionStore store, String sessionId, Map<String, Object> sessionData)
    {
        super(session);
        this.store = store;
        this.sessionId = sessionId;
        this.sessionData = sessionData;
    }

    @Override
    public void invalidate()
    {
        sessionData.clear();
        store.deleteSession(getId());
    }

    @Override
    public String getId()
    {
        return sessionId;
    }

    @Override
    public Object getAttribute(String key)
    {
        return this.sessionData.get(key);
    }

    @Override
    public Enumeration<String> getAttributeNames()
    {
        return Collections.enumeration(sessionData.keySet());
    }

    @Override
    public void removeAttribute(String key)
    {
        sessionData.remove(key);
        store.onRemoveAttribute(sessionId, key, sessionData, getMaxInactiveInterval());
    }

    @Override
    public void setAttribute(String key, Object value)
    {
        sessionData.put(key, value);
        store.onSetAttribute(sessionId, key, sessionData, getMaxInactiveInterval());
    }
}

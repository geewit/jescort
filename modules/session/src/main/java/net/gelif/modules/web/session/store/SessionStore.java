package net.gelif.modules.web.session.store;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-16
 * Time: 下午2:32
 */

import java.util.Map;

public abstract class SessionStore
{
    private boolean isSaveSessionDataOnAttributeChange = false;
    
    public boolean isSaveSessionDataOnAttributeChange()
    {
        return isSaveSessionDataOnAttributeChange;
    }
    
    /**
     * @param isSaveSessionDataOnAttributeChange
     *            default is false
     */
    public void setSaveSessionDataOnAttributeChange(boolean isSaveSessionDataOnAttributeChange)
    {
        this.isSaveSessionDataOnAttributeChange = isSaveSessionDataOnAttributeChange;
    }
    
    public abstract void saveSession(String sessionId, Map<String, Object> sessionData, int timeoutSeconds);
    
    public abstract void deleteSession(String sessionId);
    
    public abstract Map<String, Object> getSession(String sessionId, int timeoutSeconds);
    
    public void onSetAttribute(String sessionId, String key, Map<String, Object> sessionData, int timeoutSeconds)
    {
        if(isSaveSessionDataOnAttributeChange)
        {
            saveSession(sessionId, sessionData, timeoutSeconds);
        }
    }
    
    public void onRemoveAttribute(String sessionId, String key, Map<String, Object> sessionData, int timeoutSeconds)
    {
        if(isSaveSessionDataOnAttributeChange)
        {
            saveSession(sessionId, sessionData, timeoutSeconds);
        }
    }
}

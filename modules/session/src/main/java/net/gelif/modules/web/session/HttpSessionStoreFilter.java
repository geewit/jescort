package net.gelif.modules.web.session;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-16
 * Time: 下午2:51
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.gelif.kernel.core.util.CookieUtils;
import net.gelif.modules.web.session.store.SessionStore;
import net.gelif.modules.web.session.wrapper.HttpServletRequestSessionWrapper;
import net.gelif.modules.web.session.wrapper.HttpSessionSessionStoreWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class HttpSessionStoreFilter extends OncePerRequestFilter implements Filter
{
    private static Log log = LogFactory.getLog(HttpSessionStoreFilter.class);
    private String sessionIdCookieName = "_rapid_session_id";
    
    private String cookieDomain = null;
    
    SessionStore sessionStore;
    
    @Override
    protected void initFilterBean() throws ServletException
    {
        super.initFilterBean();
        //		cookieDomain = FilterConfigUtils.getParameter(getFilterConfig(), "cookieDomain", cookieDomain);
        //		cookiePath = FilterConfigUtils.getParameter(getFilterConfig(), "cookiePath", cookiePath);
        //		sessionIdCookieName = FilterConfigUtils.getParameter(getFilterConfig(), "sessionIdCookieName", sessionIdCookieName);
        
        sessionStore = lookSessionStore();
        //		wac.getAutowireCapableBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT, false);
    }
    
    protected SessionStore lookSessionStore()
    {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        SessionStore store = wac.getBean("sessionStore", SessionStore.class);
        if(logger.isInfoEnabled())
        {
            logger.info("Using '" + store.getClass().getSimpleName() + "' SessionStore for HttpSessionStoreFilter");
        }
        return store;
    }
    
    public void setSessionIdCookieName(String sessionIdCookieName)
    {
        this.sessionIdCookieName = sessionIdCookieName;
    }
    
    public void setCookieDomain(String cookieDomain)
    {
        this.cookieDomain = cookieDomain;
    }
    
    public void setSessionStore(SessionStore sessionStore)
    {
        this.sessionStore = sessionStore;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        Cookie sessionIdCookie = getOrGenerateSessionId(request, response);
        String sessionId = sessionIdCookie.getValue();
        
        HttpSession rawSession = request.getSession();
        
        Map<String, Object> sessionData = loadSessionData(sessionId, rawSession);
        try
        {
            HttpSession sessionWrapper = new HttpSessionSessionStoreWrapper(rawSession, sessionStore, sessionId, sessionData);
            chain.doFilter(new HttpServletRequestSessionWrapper(request, sessionWrapper), response);
        }
        finally
        {
            try
            {
                sessionStore.saveSession(sessionId, sessionData, rawSession.getMaxInactiveInterval());
            }
            catch(Exception e)
            {
                log.warn("save session data error,cause:" + e, e);
            }
        }
    }
    
    private Map<String, Object> loadSessionData(String sessionId, HttpSession rawSession)
    {
        Map<String, Object> sessionData = null;
        try
        {
            sessionData = sessionStore.getSession(sessionId, rawSession.getMaxInactiveInterval());
        }
        catch(Exception e)
        {
            sessionData = new HashMap<String, Object>();
            log.warn("load session data error,cause:" + e, e);
        }
        return sessionData;
    }
    
    private Cookie getOrGenerateSessionId(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Cookie> cookieMap = CookieUtils.toMap(request.getCookies());
        Cookie sessionIdCookie = cookieMap.get(sessionIdCookieName);
        if(sessionIdCookie == null || StringUtils.isEmpty(sessionIdCookie.getValue()))
        {
            sessionIdCookie = generateCookie(request, response);
        }
        else
        {
            sessionIdCookie.setMaxAge(request.getSession().getMaxInactiveInterval() * 60 * 60 * 1000);
        }
        return sessionIdCookie;
    }
    
    private Cookie generateCookie(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie sessionIdCookie;
        //		String sid = request.getSession().getId();
        String sid = null;
        if(StringUtils.isBlank(sid))
        {
            sid = generateUUID();
        }
        sessionIdCookie = new Cookie(sessionIdCookieName, sid);
        if(cookieDomain != null)
        {
            sessionIdCookie.setDomain(cookieDomain);
        }
        sessionIdCookie.setPath(request.getContextPath());
        sessionIdCookie.setMaxAge(request.getSession().getMaxInactiveInterval());
        response.addCookie(sessionIdCookie);
        return sessionIdCookie;
    }
    
    private String generateUUID()
    {
        String uuid = UUID.randomUUID().toString();
        return org.springframework.util.StringUtils.replace(uuid, "-", "");
    }
}

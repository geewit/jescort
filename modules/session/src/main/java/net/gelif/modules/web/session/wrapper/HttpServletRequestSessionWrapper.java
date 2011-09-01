package net.gelif.modules.web.session.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-16
 * Time: 下午2:58
 */
public class HttpServletRequestSessionWrapper extends javax.servlet.http.HttpServletRequestWrapper
{
    HttpSession session;

    public HttpServletRequestSessionWrapper(HttpServletRequest request, HttpSession session)
    {
        super(request);
        this.session = session;
    }

    public HttpSession getSession(boolean create)
    {
        return session;
    }

    public HttpSession getSession()
    {
        return session;
    }
}

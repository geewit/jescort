package net.gelif.kernel.core.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils
{
    public static String getServletPath(HttpServletRequest request)
    {
        String servletPath = request.getServletPath();
        
        String requestUri = request.getRequestURI();
        // Detecting other characters that the servlet container cut off (like anything after ';')
        if(requestUri != null && servletPath != null && !requestUri.endsWith(servletPath))
        {
            int pos = requestUri.indexOf(servletPath);
            if(pos > -1)
            {
                servletPath = requestUri.substring(requestUri.indexOf(servletPath));
            }
        }
        
        if(null != servletPath && !"".equals(servletPath))
        {
            return servletPath;
        }
        
        int startIndex = request.getContextPath().equals("") ? 0 : request.getContextPath().length();
        int endIndex = request.getPathInfo() == null ? requestUri.length() : requestUri.lastIndexOf(request.getPathInfo());
        
        if(startIndex > endIndex)
        { // this should not happen
            endIndex = startIndex;
        }
        
        return requestUri.substring(startIndex, endIndex);
    }
    
    public static String getResourceBase(HttpServletRequest req)
    {
        String path = getServletPath(req);
        if(path == null || "".equals(path))
        {
            return "";
        }
        
        return path.substring(0, path.lastIndexOf('/'));
    }
}

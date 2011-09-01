package net.gelif.kernel.core.util;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-16
 * Time: 下午3:02
 */
public class CookieUtils
{
    public static Map<String, Cookie> toMap(Cookie[] cookies)
    {
        if (cookies == null || cookies.length == 0)
        {
            return new ConcurrentHashMap<String, Cookie>(0);
        }

        Map<String, Cookie> map = new ConcurrentHashMap<String, Cookie>(cookies.length * 2);
        for(Cookie cookie : cookies)
        {
            map.put(cookie.getName(), cookie);
        }
        return map;
    }
}

package net.jescort.web.jsp.taglib;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-19
 * Time: 下午1:18
 */
public class SubstringFunction
{
    public static String substring(String str, int width)
    {
        return substring(str, width, "...");
    }

    public static String substring(String str, int width, String ellipsis)
    {
        if (StringUtils.isBlank(str))
        {
            return StringUtils.EMPTY;
        }

        if (str.length() <= width)
        {
            return str;
        }

        String regex = "[\u4e00-\u9fa5\ufe30-\uffa0]+$";

        int w = 0;// string width
        int l = 0;// string length
        for(char s : str.toCharArray())
        {
            w = String.valueOf(s).matches(regex) ? w + 2 : w + 1;
            if (w > width)
            {
                break;
            }
            l++;
        }
        return w > width ? str.substring(0, l) + ellipsis : str.substring(0, l);
    }
}

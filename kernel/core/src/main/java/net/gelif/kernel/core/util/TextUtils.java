package net.gelif.kernel.core.util;

public class TextUtils
{
    public static String substringChinese(String s, int len, String encoding)
    {
        try
        {
            byte[] src = s.getBytes("ISO-8859-1");
            String t = new String(src, encoding);
            
            if(t.length() <= len)
            {
                return s;
            }
            else
            {
                t = t.substring(0, len);
            }
            
            byte[] dest = t.getBytes(encoding);
            String ds = new String(dest, "ISO-8859-1");
            return ds;
        }
        catch(Exception e)
        {
            return s;
        }
    }
}

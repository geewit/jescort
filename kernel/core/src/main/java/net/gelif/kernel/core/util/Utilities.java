package net.gelif.kernel.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-4-20
 * Time: 上午8:39
 */
public class Utilities
{
    public static String stripJsessionId(String url)
    {
        // Strip off jsessionid found in referer URL
        int startPos = url.indexOf(";jsessionid=");
        if (startPos != -1)
        {
            int endPos = url.indexOf("?", startPos);
            if (endPos == -1)
            {
                url = url.substring(0, startPos);
            } else
            {
                url = url.substring(0, startPos) + url.substring(endPos, url.length());
            }
        }
        return url;
    }

    public static String escapeHTML(String s)
    {
        return escapeHTML(s, true);
    }

    public static String escapeHTML(String s, boolean escapeAmpersand)
    {
        // got to do amp's first so we don't double escape
        if (escapeAmpersand)
        {
            s = StringUtils.replace(s, "&", "&amp;");
        }
        s = StringUtils.replace(s, "&nbsp;", " ");
        s = StringUtils.replace(s, "\"", "&quot;");
        s = StringUtils.replace(s, "<", "&lt;");
        s = StringUtils.replace(s, ">", "&gt;");
        return s;
    }

    public static String unescapeHTML(String str)
    {
        return StringEscapeUtils.unescapeHtml(str);
    }

    public static String removeHTML(String str)
    {
        return removeHTML(str, true);
    }

    public static String removeHTML(String str, boolean addSpace)
    {
        if (str == null)
        {
            return "";
        }
        StringBuffer ret = new StringBuffer(str.length());
        int start = 0;
        int beginTag = str.indexOf("<");
        int endTag = 0;
        if (beginTag == -1)
        {
            return str;
        }

        while (beginTag >= start)
        {
            if (beginTag > 0)
            {
                ret.append(str.substring(start, beginTag));

                // replace each tag with a space (looks better)
                if (addSpace)
                {
                    ret.append(" ");
                }
            }
            endTag = str.indexOf(">", beginTag);

            // if endTag found move "cursor" forward
            if (endTag > -1)
            {
                start = endTag + 1;
                beginTag = str.indexOf("<", start);
            }
            // if no endTag found, findOne rest of str and break
            else
            {
                ret.append(str.substring(beginTag));
                break;
            }
        }
        // append everything after the last endTag
        if (endTag > -1 && endTag + 1 < str.length())
        {
            ret.append(str.substring(endTag + 1));
        }
        return ret.toString().trim();
    }

    public static String removeAndEscapeHTML(String s)
    {
        if (s == null)
        {
            return "";
        } else
        {
            return Utilities.escapeHTML(Utilities.removeHTML(s));
        }
    }

    public static String autoformat(String s)
    {
        String ret = StringUtils.replace(s, "\n", "<br />");
        return ret;
    }

    public static String replaceNonAlphanumeric(String str)
    {
        return replaceNonAlphanumeric(str, '_');
    }

    public static String replaceNonAlphanumeric(String str, char subst)
    {
        StringBuffer ret = new StringBuffer(str.length());
        char[] testChars = str.toCharArray();
        for(int i = 0; i < testChars.length; i++)
        {
            if (Character.isLetterOrDigit(testChars[i]))
            {
                ret.append(testChars[i]);
            } else
            {
                ret.append(subst);
            }
        }
        return ret.toString();
    }

    public static String removeNonAlphanumeric(String str)
    {
        StringBuffer ret = new StringBuffer(str.length());
        char[] testChars = str.toCharArray();
        for(int i = 0; i < testChars.length; i++)
        {
            // MR: Allow periods in page links
            if (Character.isLetterOrDigit(testChars[i]) || testChars[i] == '.')
            {
                ret.append(testChars[i]);
            }
        }
        return ret.toString();
    }

    public static String stringArrayToString(String[] stringArray, String delim)
    {
        String ret = "";
        for(int i = 0; i < stringArray.length; i++)
        {
            if (ret.length() > 0)
            {
                ret = ret + delim + stringArray[i];
            } else
            {
                ret = stringArray[i];
            }
        }
        return ret;
    }

    public static String[] stringToStringArray(String instr, String delim) throws NoSuchElementException, NumberFormatException
    {
        StringTokenizer toker = new StringTokenizer(instr, delim);
        String stringArray[] = new String[toker.countTokens()];
        int i = 0;

        while (toker.hasMoreTokens())
        {
            stringArray[i++] = toker.nextToken();
        }
        return stringArray;
    }

    public static int[] stringToIntArray(String instr, String delim) throws NoSuchElementException, NumberFormatException
    {
        StringTokenizer toker = new StringTokenizer(instr, delim);
        int intArray[] = new int[toker.countTokens()];
        int i = 0;

        while (toker.hasMoreTokens())
        {
            String sInt = toker.nextToken();
            int nInt = Integer.parseInt(sInt);
            intArray[i++] = new Integer(nInt).intValue();
        }
        return intArray;
    }

    public static String intArrayToString(int[] intArray)
    {
        String ret = "";
        for(int i = 0; i < intArray.length; i++)
        {
            if (ret.length() > 0)
            {
                ret = ret + "," + Integer.toString(intArray[i]);
            } else
            {
                ret = Integer.toString(intArray[i]);
            }
        }
        return ret;
    }

    public static void copyFile(File from, File to) throws IOException
    {
        InputStream in = null;
        OutputStream out = null;

        try
        {
            in = new FileInputStream(from);
        } catch (IOException ex)
        {
            throw new IOException("Utilities.copyFile: opening input stream '" + from.getPath() + "', " + ex.getMessage());
        }

        try
        {
            out = new FileOutputStream(to);
        } catch (Exception ex)
        {
            try
            {
                in.close();
            } catch (IOException ex1)
            {
            }
            throw new IOException("Utilities.copyFile: opening output stream '" + to.getPath() + "', " + ex.getMessage());
        }

        copyInputToOutput(in, out, from.length());
    }

    public static void copyInputToOutput(InputStream input, OutputStream output, long byteCount) throws IOException
    {
        int bytes;
        long length;

        BufferedInputStream in = new BufferedInputStream(input);
        BufferedOutputStream out = new BufferedOutputStream(output);

        byte[] buffer;
        buffer = new byte[8192];

        for(length = byteCount; length > 0; )
        {
            bytes = (int) (length > 8192 ? 8192 : length);

            try
            {
                bytes = in.read(buffer, 0, bytes);
            } catch (IOException ex)
            {
                try
                {
                    in.close();
                    out.close();
                } catch (IOException ex1)
                {
                }
                throw new IOException("Reading input stream, " + ex.getMessage());
            }

            if (bytes < 0)
            {
                break;
            }

            length -= bytes;

            try
            {
                out.write(buffer, 0, bytes);
            } catch (IOException ex)
            {
                try
                {
                    in.close();
                    out.close();
                } catch (IOException ex1)
                {
                }
                throw new IOException("Writing output stream, " + ex.getMessage());
            }
        }

        try
        {
            in.close();
            out.close();
        } catch (IOException ex)
        {
            throw new IOException("Closing file streams, " + ex.getMessage());
        }
    }

    //------------------------------------------------------------------------
    public static void copyInputToOutput(InputStream input, OutputStream output) throws IOException
    {
        BufferedInputStream in = new BufferedInputStream(input);
        BufferedOutputStream out = new BufferedOutputStream(output);
        byte buffer[] = new byte[8192];
        for(int count = 0; count != -1; )
        {
            count = in.read(buffer, 0, 8192);
            if (count != -1)
            {
                out.write(buffer, 0, count);
            }
        }

        try
        {
            in.close();
            out.close();
        } catch (IOException ex)
        {
            throw new IOException("Closing file streams, " + ex.getMessage());
        }
    }
}

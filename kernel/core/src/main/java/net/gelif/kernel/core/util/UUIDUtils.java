package net.gelif.kernel.core.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.util.UUID;

/**
 * An UUID utility class.
 */
public final class UUIDUtils
{
    /**
     * {@value}
     */
    public static final String UUID_ZERO = "00000000-0000-0000-0000-000000000000";
    public static final int UUID_LENGTH = 36;

    /**
     * Generates a random string UUID.
     *
     * @return uuid
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * Validate whether the given UUID.
     *
     * @param value uuid
     * @return true or false
     */
    public static boolean isUUID(String value)
    {
        if (null == value || value.length() != UUID_LENGTH)
        {
            return false;
        }
        int i = 0;
        for(int p = 0; p < value.length(); p++)
        {
            char ch = value.charAt(p);
            if (ch == UUID_SPLITER)
            {
                if (i >= UUID_SPLIT_INDEX.length - 1 || p != UUID_SPLIT_INDEX[i])
                {
                    return false;
                }
                i++;
                continue;
            }
            if (p >= UUID_SPLIT_INDEX[i])
            {
                return false;
            }
            if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f'))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns !isUUID()
     *
     * @param value uuid
     * @return true or false
     */
    public static boolean isNotUUID(String value)
    {
        return !isUUID(value);
    }

    /**
     * Converts string uuid to bytes array
     *
     * @param value uuid
     * @return byte array with lengh 16
     */
    public static byte[] stringToBytes(String value)
    {
        if (value.length() != UUID_LENGTH)
        {
            throw new IllegalArgumentException("Invalid UUID string: " + value);
        }
        char[] chs = new char[UUID_LENGTH];
        int i = 0;
        int j = 0;
        for(int p = 0; p < value.length(); p++)
        {
            char ch = value.charAt(p);
            if (ch == '-')
            {
                if (i >= UUID_SPLIT_INDEX.length - 1 || p != UUID_SPLIT_INDEX[i])
                {
                    throw new IllegalArgumentException("Invalid UUID string: " + value);
                }
                i++;
                continue;
            }
            if (p >= UUID_SPLIT_INDEX[i])
            {
                throw new IllegalArgumentException("Invalid UUID string: " + value);
            }
            if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f'))
            {
                throw new IllegalArgumentException("Invalid UUID string: " + value);
            }
            chs[j] = ch;
            j++;
        }
        try
        {
            return Hex.decodeHex(chs);
        } catch (DecoderException e)
        {
            throw new IllegalArgumentException("Invalid UUID string: " + value, e);
        }
    }

    /**
     * Converts bytes array uuid to string
     *
     * @param value uuid
     * @return string with length 36
     */
    public static String bytesToString(byte[] value)
    {
        if (value.length != 16)
        {
            throw new IllegalArgumentException("Invalid UUID bytes");
        }
        char[] chs = Hex.encodeHex(value);
        StringBuffer sb = new StringBuffer(36);
        sb.append(chs, 0, 7).append(UUID_SPLITER).append(chs, 8, 4).append(UUID_SPLITER).append(chs, 12, 4).append(UUID_SPLITER).append(chs, 16, 4).append(UUID_SPLITER).append(chs, 20, 16);
        return sb.toString();
    }

    private static final byte[] UUID_SPLIT_INDEX = {8, 13, 18, 23, Byte.MAX_VALUE};
    private static final char UUID_SPLITER = '-';
}

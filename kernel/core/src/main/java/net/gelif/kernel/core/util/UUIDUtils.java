package net.gelif.kernel.core.util;


import org.apache.commons.codec.binary.Hex;

/**
 * An UUID utility class.
 */
public final class UUIDUtils
{
    /**
     * {@value}
     */
    public static final String UUID_ZERO = "00000000000000000000000000000000";
    public static final int UUID_LENGTH = 32;

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
            if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f') && (ch < 'A' || ch > 'F'))
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
        sb.append(chs, 0, 7).append(chs, 8, 4).append(chs, 12, 4).append(chs, 16, 4).append(chs, 20, 16);
        return sb.toString();
    }
}

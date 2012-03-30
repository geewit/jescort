/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
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

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

public class IntegerIdUtils
{
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    public static String integer2string(int i)
    {
        char buf[] = new char[33];
        int charPos = buf.length - 1;

        while (i >= DIGITS.length)
        {
            buf[charPos--] = DIGITS[i % DIGITS.length];
            i = (i / DIGITS.length);
        }
        buf[charPos] = DIGITS[i];
        return new String(buf, charPos, (buf.length - charPos));
    }

    public static int string2integer(String s)
    {
        return parseInteger(s);
    }

    private static int parseInteger(String s)
    {
        if (s == null)
        {
            return 0;
        }
        int len = s.length();
        if (len == 0)
        {
            return 0;
        }

        int result = 0;
        int i = 0;

        while (i < len)
        {
            int digit = digit(s.charAt(i++));
            result *= DIGITS.length;
            result += digit;
        }

        return result;
    }

    private static int digit(char ch)
    {
        if (ch <= '9')
        {
            return (ch - DIGITS[0]);
        } else if (ch <= 'Z')
        {
            return (ch - DIGITS[10] + 10);
        } else
        {
            return (ch - DIGITS[36] + 36);
        }
    }
}

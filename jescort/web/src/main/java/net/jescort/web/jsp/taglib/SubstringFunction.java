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

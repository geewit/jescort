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

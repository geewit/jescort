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

import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-1-30
 * Time: 上午11:41
 */
public class LocaleUtils
{
    private final static ConcurrentHashMap<String, Locale> cache = new ConcurrentHashMap<String, Locale>(16);

    private static Locale createSingleton(String key, String language, String country)
    {
        Locale locale = new Locale(language, country);
        cache.put(key, locale);
        return locale;
    }

    public static final Locale ENGLISH = createSingleton("en", "en", "");

    public static final Locale CHINESE = createSingleton("zh", "zh", "");

    public static final Locale SIMPLIFIED_CHINESE = createSingleton("zh_CN", "zh", "CN");

    public static final Locale TRADITIONAL_CHINESE = createSingleton("zh_TW", "zh", "TW");

    public static final Locale CHINA = SIMPLIFIED_CHINESE;

    public static final Locale PRC = SIMPLIFIED_CHINESE;

    public static final Locale TAIWAN = TRADITIONAL_CHINESE;

    public static final Locale US = createSingleton("en_US", "en", "US");

    public static Set<String> ALL_AVALIABLE_LOCALES = cache.keySet();
}

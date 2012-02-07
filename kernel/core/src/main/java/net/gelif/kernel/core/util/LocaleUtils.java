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

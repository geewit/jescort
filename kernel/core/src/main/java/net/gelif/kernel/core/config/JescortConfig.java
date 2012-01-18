package net.gelif.kernel.core.config;

import net.gelif.kernel.core.AbstractLogger;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class JescortConfig extends AbstractLogger
{
    private static final String properties_name = "jescort.properties";

    private static volatile JescortConfig properties;

    private Configuration config;

    private JescortConfig()
    {
    }

    public static JescortConfig getInstance(String propertiesName)
    {
        if (properties == null)
        {
            synchronized (JescortConfig.class)
            {
                if (null == properties)
                {
                    synchronized (JescortConfig.class)
                    {
                        properties = new JescortConfig();
                        try
                        {
                            properties.setConfig(new PropertiesConfiguration(propertiesName));
                        } catch (ConfigurationException ignore)
                        {
                        }
                    }
                }
            }
        }
        return properties;
    }

    public static JescortConfig getInstance()
    {
        return getInstance(properties_name);
    }

    public Configuration getConfig()
    {
        return config;
    }

    public void setConfig(Configuration config)
    {
        this.config = config;
    }

    public static String getProperty(String key)
    {
        String value = getInstance().getConfig().getString(key);
        return value == null ? value : value.trim();
    }

    public static String getProperty(String key, String defaultValue)
    {
        String value = getProperty(key);
        if(value == null)
        {
            return defaultValue;
        }

        return value.trim();
    }

    public static boolean getBooleanProperty(String key)
    {
        return getBooleanProperty(key, false);
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue)
    {
        String value = getProperty(key);

        if(value == null)
        {
            return defaultValue;
        }

        return (new Boolean(value)).booleanValue();
    }
}

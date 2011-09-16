package net.gelif.kernel.core.config;

import net.gelif.kernel.core.AbstractLogger;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Properties extends AbstractLogger
{
    private static final String properties_name = "jescort.properties";
    
    private static volatile Properties properties;
    
    private Configuration config;
    
    public Properties()
    {
    }
    
    public static Properties getInstance(String propertiesName)
    {
        if(properties == null)
        {
            synchronized(Properties.class)
            {
                if(null == properties)
                {
                    synchronized(Properties.class)
                    {
                        properties = new Properties();
                        try
                        {
                            properties.setConfig(new PropertiesConfiguration(propertiesName));
                        }
                        catch(ConfigurationException ignore)
                        {
                        }
                    }
                }
            }
        }
        return properties;
    }
    
    public static Properties getInstance()
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
}

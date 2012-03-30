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
package net.gelif.modules.bbcode;

import net.gelif.modules.bbcode.configuration.Configuration;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Create the text processor configuration
 *
 * @author admin@gelif.net
 */
public final class ConfigurationFactory
{
    // Helper constants
    private static final String DEFAULT_USER_CONFIGURATION = "bbcode";
    private static final String CONFIGURATION_EXTENSION = ".xml";

    // Configuration paths
    public static final String DEFAULT_USER_CONFIGURATION_FILE = DEFAULT_USER_CONFIGURATION + CONFIGURATION_EXTENSION;
    public static final String DEFAULT_CONFIGURATION_FILE = DEFAULT_USER_CONFIGURATION + CONFIGURATION_EXTENSION;

    //public static final String DEFAULT_PROPERTIES_FILE = "bbcode.properties";
    //public static final String DEFAULT_PROPERTIES_XML_FILE = "bbcode.properties.xml";

    private final DomConfigurationFactory domConfigurationFactory = DomConfigurationFactory.getInstance();

    /**
     * Singletone class instance
     */
    private static final ConfigurationFactory instance = new ConfigurationFactory();

    /**
     * private constructor
     */
    private ConfigurationFactory()
    {
    }

    /**
     * Return instance of class ConfigurationFactory
     *
     * @return configuration factory
     */
    public static ConfigurationFactory getInstance()
    {
        return instance;
    }

    /**
     * Create the default bb-code processor.
     *
     * @return Default bb-code processor
     * @throws TextProcessorFactoryException when can't read the default code set resource
     */
    public Configuration create()
    {
        Configuration configuration;
        try
        {
            InputStream stream = null;
            try
            {
                // Search the auth configuration
                stream = Util.openResourceStream(DEFAULT_USER_CONFIGURATION_FILE);

                // If auth configuration not found then use default
                if (stream == null)
                {
                    stream = Util.openResourceStream(DEFAULT_CONFIGURATION_FILE);
                }

                if (stream != null)
                {
                    configuration = create(stream);
                } else
                {
                    throw new TextProcessorFactoryException("Can't find or open resource.");
                }
            } finally
            {
                if (stream != null)
                {
                    stream.close();
                }
            }
        } catch (IOException e)
        {
            throw new TextProcessorFactoryException(e);
        }
        return configuration;
    }

    /**
     * Create the bb-processor using xml-configuration resource
     *
     * @param resourceName name of resource file
     * @return bb-code processor
     * @throws TextProcessorFactoryException when can't find or read the resource or illegal config file
     */
    public Configuration createFromResource(String resourceName)
    {
        if (resourceName == null)
        {
            throw new IllegalArgumentException("The resource name is not setted.");
        }

        Configuration configuration;
        try
        {
            InputStream stream = null;
            try
            {
                stream = Util.openResourceStream(resourceName);

                if (stream != null)
                {
                    configuration = create(stream);
                } else
                {
                    throw new TextProcessorFactoryException("Can't find or open resource \"" + resourceName + "\".");
                }
            } finally
            {
                if (stream != null)
                {
                    stream.close();
                }
            }
        } catch (IOException e)
        {
            throw new TextProcessorFactoryException(e);
        }

        return configuration;
    }

    /**
     * Create the bb-code processor from file with XML-configuration.
     *
     * @param fileName name of file with configuration
     * @return bb-code processor
     */
    public Configuration create(String fileName)
    {
        return create(new File(fileName));
    }

    /**
     * Create the bb-code processor from file with XML-configuration.
     *
     * @param file file with configuration
     * @return bb-code processor
     */
    public Configuration create(File file)
    {
        try
        {
            Configuration configuration;
            InputStream stream = new BufferedInputStream(new FileInputStream(file));
            try
            {
                configuration = create(stream);
            } finally
            {
                stream.close();
            }
            return configuration;
        } catch (IOException e)
        {
            throw new TextProcessorFactoryException(e);
        }
    }

    /**
     * Create the bb-processor from XML InputStream
     *
     * @param stream the input stream with XML
     * @return bb-code processor
     * @throws TextProcessorFactoryException when can't build Document
     */
    public Configuration create(InputStream stream)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(stream);
            return domConfigurationFactory.create(document);
        } catch (ParserConfigurationException e)
        {
            throw new TextProcessorFactoryException(e);
        } catch (IOException e)
        {
            throw new TextProcessorFactoryException(e);
        } catch (SAXException e)
        {
            throw new TextProcessorFactoryException(e);
        }
    }

}

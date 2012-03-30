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

/**
 * The class for creating the escape xml special symbols processor. It's
 * processor change:
 * <p/>
 * &amp; to &amp;amp; &apos; to &amp;apos; &lt; to &amp;lt; &gt; to &amp;gt;
 * &quot; to &amp;quot;
 *
 * @author admin@gelif.net
 */
public final class EscapeXmlProcessorFactory implements TextProcessorFactory
{
    /**
     * The default XML escape symbols
     */
    private static final String[][] DEFAULT_ESCAPE_XML = {{"&", "&amp;"}, {"'", "&apos;"}, {">", "&gt;"}, {"<", "&lt;"}, {"\"", "&quot;"}};

    /**
     * Instance of processor.
     */
    private static final TextProcessor processor = new EscapeProcessor(DEFAULT_ESCAPE_XML);

    /**
     * Instance of factory
     */
    private static final TextProcessorFactory instance = new EscapeXmlProcessorFactory();

    /**
     * Private constructor. Because this class is singleton.
     */
    private EscapeXmlProcessorFactory()
    {
    }

    /**
     * Return instance of this class.
     *
     * @return instance of escape xml processor factory
     */
    public static TextProcessorFactory getInstance()
    {
        return instance;
    }

    /**
     * Create the new XML escape symbols processor.
     *
     * @see TextProcessorFactory#create()
     */
    public TextProcessor create()
    {
        return processor;
    }
}

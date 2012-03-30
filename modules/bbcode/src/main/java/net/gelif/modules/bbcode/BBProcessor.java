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

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * The bbcode processor. You can use the standard code set or define other.
 *
 * @author admin@gelif.net
 */
public final class BBProcessor extends TextProcessorAdapter
{
    /**
     * BB-codes
     */
    private WScope scope = null;
    private WTemplate prefix = null;
    private WTemplate suffix = null;
    private Map<String, Object> params = null;

    /**
     * Create the bbcode processor
     */
    public BBProcessor()
    {
    }

    /**
     * Process bbcodes <br/>
     * 1. Escape the xml special symbols<br/>
     * 2. replace bbcodes to HTML-tags<br/>
     * 3. replace symbols \r\n to HTML-tag "&lt;br/&gt;"<br/>
     *
     * @param source the source string
     * @return result string
     * @see TextProcessor#process(CharSequence)
     */
    public CharSequence process(CharSequence source)
    {
        Context context = new Context();
        StringBuilder target = new StringBuilder();
        context.setTarget(target);
        context.setSource(new Source(source));
        context.setScope(scope);
        if (params != null)
        {
            for(Map.Entry<String, Object> entry : params.entrySet())
            {
                context.setAttribute(entry.getKey(), entry.getValue());
            }
        }

        try
        {
            prefix.generate(context);
            context.parse();
            suffix.generate(context);
        } catch (IOException e)
        {
            // Never because StringBuilder not throw IOException
        }

        return target;
    }

    /**
     * Set the root scope of text processor.
     *
     * @param scope root code scope
     * @throws IllegalStateException if scope already setted
     */
    public void setScope(WScope scope) throws IllegalStateException
    {
        if (this.scope == null)
        {
            this.scope = scope;
        } else
        {
            throw new IllegalStateException("Can't change the root scope.");
        }
    }

    /**
     * Set the prefix for text processor
     *
     * @param prefix template wich uses to create prefix
     * @throws IllegalStateException If prefix already setted
     */
    public void setPrefix(WTemplate prefix) throws IllegalStateException
    {
        if (this.prefix == null)
        {
            this.prefix = prefix;
        } else
        {
            throw new IllegalStateException("Can't change the prefix.");
        }
    }

    /**
     * Set the suffix for text processor
     *
     * @param suffix template wich uses to create prefix
     * @throws IllegalStateException If suffix already setted
     */
    public void setSuffix(WTemplate suffix)
    {
        if (this.suffix == null)
        {
            this.suffix = suffix;
        } else
        {
            throw new IllegalStateException("Can't change the suffix.");
        }
    }

    /**
     * Set text processor parameters map.
     *
     * @param params parameters
     */
    public void setParams(Map<String, Object> params)
    {
        if (this.params == null)
        {
            this.params = Collections.unmodifiableMap(params);
        } else
        {
            throw new IllegalStateException("Can't change parameters.");
        }
    }
}

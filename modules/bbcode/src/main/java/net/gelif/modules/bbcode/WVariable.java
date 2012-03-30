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

import java.util.regex.Matcher;

/**
 * @author admin@gelif.net
 */
public class WVariable extends WNamedElement implements WPatternElement
{
    private final java.util.regex.Pattern regex;

    public WVariable(String name)
    {
        super(name);
        regex = null;
    }

    /**
     * Create named variable
     *
     * @param name  variable name
     * @param regex regular expression pattern
     */
    public WVariable(String name, java.util.regex.Pattern regex)
    {
        super(name);
        this.regex = regex;
    }

    public boolean parse(Context context, WPatternElement terminator)
    {
        int end;
        if (terminator != null)
        {
            end = terminator.findIn(context.getSource());
        } else
        {
            end = context.getSource().getLength();
        }

        if (end < 0)
        {
            return false;
        }

        Source source = context.getSource();
        CharSequence value = source.sub(end);

        // If define regex, then find this regex in value
        if (regex != null)
        {
            Matcher matcher = regex.matcher(value);
            if (matcher.lookingAt())
            {
                int lend = matcher.end();
                end = source.getOffset() + lend;
                value = value.subSequence(0, lend);
            } else
            {
                return false;
            }
        }

        // Test this variable already defined and equals with this in this code scope 
        Object attr = context.getLocalAttribute(getName());
        if (attr == null || attr.equals(value))
        {
            if (attr == null)
            {
                setAttribute(context, value);
            }
            source.setOffset(end);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isNextIn(Source source)
    {
        return regex != null && regex.matcher(source.subToEnd()).lookingAt();
    }

    /**
     * Find this element
     *
     * @param source text source
     * @return start offset
     */
    public int findIn(Source source)
    {
        if (regex != null)
        {
            Matcher matcher = regex.matcher(source.subToEnd());
            if (matcher.find())
            {
                return matcher.start();
            } else
            {
                return -1;
            }
        } else
        {
            return -1;
        }
    }

    @Override
    public String toString()
    {
        return "variable:" + getName();
    }
}

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

import net.gelif.modules.bbcode.configuration.Code;

import java.io.IOException;

/**
 * The bbcode class
 *
 * @author admin@gelif.net
 */
public class WCode extends AbstractCode
{
    /**
     * Pattern for parsing code
     */
    private final WPattern pattern;

    /**
     * Create bb-code with default name and zero priority.
     *
     * @param pattern  parse pattern
     * @param template building tamplate
     */
    public WCode(WPattern pattern, WTemplate template)
    {
        super(template, Util.generateRandomName(), Code.DEFAULT_PRIORITY);
        this.pattern = pattern;
    }

    /**
     * Create the bb-code with priority
     *
     * @param pattern  pattern to parse the source text
     * @param template template to build target text
     * @param name     name of code
     * @param priority priority. If priority higher then code be checking early.
     */
    public WCode(WPattern pattern, WTemplate template, String name, int priority)
    {
        super(template, name, priority);
        this.pattern = pattern;
    }

    /**
     * Parse bb-code
     * <p/>
     * Before invocation suspicious method must be call
     *
     * @param context the bb-processing context
     * @return true - if parse source false - if can't parse code
     * @throws java.io.IOException if can't append to target
     */
    public boolean process(Context context) throws IOException
    {
        Context codeContext = new Context(context);
        if (pattern.parse(codeContext))
        {
            codeContext.mergeWithParent();
            template.generate(context);
            return true;
        }

        return false;
    }

    /**
     * Check if next sequence can be parsed with this code. It's most called
     * method in this project.
     *
     * @param source text source
     * @return true - if next sequence can be parsed with this code; false -
     *         only if next sequence can't be parsed with this code.
     */
    public boolean suspicious(Source source)
    {
        return pattern.suspicious(source);
    }
}

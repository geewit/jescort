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

/**
 * Abstract bb-code
 *
 * @author admin@gelif.net
 */
public abstract class AbstractCode implements Comparable<AbstractCode>
{

    /**
     * Priority. If priority higher then code be checking early.
     */
    private final int priority;

    /**
     * The code name.
     */
    private final String name;

    /**
     * template for build result char sequence
     */
    protected final WTemplate template;

    /**
     * Abstract constructor for the bb-code with priority
     *
     * @param template template
     * @param name     name of code
     * @param priority priority. If priority higher then code be checking early.
     */
    protected AbstractCode(WTemplate template, String name, int priority)
    {
        this.template = template;
        this.priority = priority;
        this.name = name;
    }

    /**
     * Process code: parse source and generate result string
     *
     * @param context current context
     * @return true if next sequence in source is valid code
     * @throws java.io.IOException append result to target
     */
    public abstract boolean process(Context context) throws IOException;

    /**
     * @param source source of text
     * @return true if next sequence can be this code
     */
    public abstract boolean suspicious(Source source);

    /**
     * Compare by priorities
     */
    public int compareTo(AbstractCode code)
    {
        return this.priority - code.priority;
    }

    /**
     * Get code name
     *
     * @return code name
     */
    public String getName()
    {
        return name;
    }
}

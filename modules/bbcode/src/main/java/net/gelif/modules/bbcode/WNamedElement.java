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
 * Named element are variable and text WPatternElement or WTemplateElement
 * @author admin@gelif.net
 */
public abstract class WNamedElement
{
    /**
     * Variable name
     */
    private final String name;

    /**
     * Create named element
     *
     * @param name name of element
     */
    protected WNamedElement(String name)
    {
        this.name = name;
    }

    /**
     * Get element name
     *
     * @return element name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Add attribute with name of this element name and value <code>value</code>
     * to <code>context</code>.
     *
     * @param context context
     * @param value   variable value
     */
    protected void setAttribute(Context context, CharSequence value)
    {
        context.setAttribute(name, value);
    }
}

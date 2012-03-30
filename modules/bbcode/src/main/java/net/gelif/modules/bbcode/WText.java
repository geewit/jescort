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
 * @author admin@gelif.net
 */
public class WText extends WNamedElement implements WPatternElement
{
    /**
     * Scope define the codeset for parsing this text
     */
    private final WScope scope;

    /**
     * Mark that variables getted in element context will be put into parent
     * context
     */
    private final boolean transparent;

    public WText(String name, boolean transparent)
    {
        super(name);
        scope = null;
        this.transparent = transparent;
    }

    public WText(String name, WScope scope, boolean transparent)
    {
        super(name);
        this.scope = scope;
        this.transparent = transparent;
    }

    public boolean parse(Context context, WPatternElement terminator)
    {
        Context child = new Context(context);
        StringBuilder target = new StringBuilder();
        child.setTarget(target);
        if (scope != null)
        {
            child.setScope(scope);
        }
        child.setTerminator(terminator);
        try
        {
            child.parse();
        } catch (IOException e)
        {
            // Never because StringBuilder don't throw IOException
        }
        if (transparent)
        {
            child.mergeWithParent();
        }
        setAttribute(context, target);
        return true;
    }

    public boolean isNextIn(Source source)
    {
        return false;
    }

    /**
     * Find this element
     *
     * @param source text source
     * @return start offset
     */
    public int findIn(Source source)
    {
        return -1;
    }

    @Override
    public String toString()
    {
        return "text:" + getName();
    }
}

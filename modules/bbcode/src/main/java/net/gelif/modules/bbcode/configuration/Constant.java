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
package net.gelif.modules.bbcode.configuration;

import net.gelif.modules.bbcode.*;

import java.util.Map;

/**
 * @author admin@gelif.net
 */
public class Constant implements TemplateElement, PatternElement
{
    private final String value;
    private boolean ignoreCase = false;

    public Constant(String value)
    {
        this.value = value;
    }

    public WConstant create()
    {
        return new WConstant(value);
    }

    public WPatternElement create(Configuration configuration, Map<Scope, WScope> scopes, Map<Code, AbstractCode> codes)
    {
        if (!ignoreCase)
        {
            return new WConstant(value);
        } else
        {
            return new WConstantIgnoreCase(value);
        }
    }

    public String getValue()
    {
        return value;
    }

    public boolean isIgnoreCase()
    {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase)
    {
        this.ignoreCase = ignoreCase;
    }
}

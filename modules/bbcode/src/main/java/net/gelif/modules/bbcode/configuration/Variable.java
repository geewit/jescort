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

import net.gelif.modules.bbcode.AbstractCode;
import net.gelif.modules.bbcode.WScope;
import net.gelif.modules.bbcode.WVariable;

import java.util.Map;

/**
 * @author admin@gelif.net
 */
public class Variable extends NamedElement implements PatternElement
{
    private final java.util.regex.Pattern regex;

    public Variable(String name)
    {
        super(name);
        this.regex = null;
    }

    public Variable(String name, java.util.regex.Pattern regex)
    {
        super(name);
        this.regex = regex;
    }

    public WVariable create(Configuration configuration, Map<Scope, WScope> scopes, Map<Code, AbstractCode> codes)
    {
        return new WVariable(name, regex);
    }
}

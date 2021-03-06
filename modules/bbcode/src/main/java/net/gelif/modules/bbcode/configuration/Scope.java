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
import net.gelif.modules.bbcode.Util;
import net.gelif.modules.bbcode.WScope;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Scope definition
 *
 * @author admin@gelif.net
 */
public final class Scope
{
    /**
     * Default name for root scope. If ROOT scope not defined in configuration
     * then all codes add to default ROOT scope.
     */
    public static final String ROOT = "ROOT";

    private final String name;
    private String parent;
    private boolean ignoreText;
    private Set<Code> codes = new HashSet<Code>();

    public Scope()
    {
        name = Util.generateRandomName();
    }

    public Scope(String name)
    {
        this.name = name;
        this.parent = null;
        this.ignoreText = false;
    }

    public Scope(String name, String parent, boolean ignoreText)
    {
        this.name = name;
        this.parent = parent;
        this.ignoreText = ignoreText;
    }

    public String getName()
    {
        return name;
    }

    public String getParent()
    {
        return parent;
    }

    public boolean isIgnoreText()
    {
        return ignoreText;
    }

    public Set<Code> getCodes()
    {
        return codes;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public void setIgnoreText(boolean ignoreText)
    {
        this.ignoreText = ignoreText;
    }

    public void setCodes(Set<Code> codes)
    {
        this.codes = codes;
    }

    public void addCode(Code code)
    {
        codes.add(code);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Scope scope = (Scope) o;

        return name.equals(scope.name);
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    /**
     * Create scope
     *
     * @param configuration text processor configuration
     * @param createdScopes created scopes
     * @param codes         codes
     * @return scope
     */
    WScope create(Configuration configuration, Map<Scope, WScope> createdScopes, Map<Code, AbstractCode> codes)
    {
        WScope created = createdScopes.get(this);
        if (created == null)
        {
            created = new WScope(getName());
            createdScopes.put(this, created);
            created.setIgnoreText(isIgnoreText());
            if (getParent() != null)
            {
                created.setParent(configuration.getScope(getParent()).create(configuration, createdScopes, codes));
            }
            Set<AbstractCode> scopeCodes = new HashSet<AbstractCode>();
            for(Code code : getCodes())
            {
                scopeCodes.add(code.create(configuration, createdScopes, codes));
            }
            created.setScopeCodes(scopeCodes);
            created.init();
        }
        return created;
    }
}

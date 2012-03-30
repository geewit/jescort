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
import net.gelif.modules.bbcode.WPattern;
import net.gelif.modules.bbcode.WPatternElement;
import net.gelif.modules.bbcode.WScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Pattern definition. For creating pattern for text parsing.
 *
 * @author admin@gelif.net
 */
public class Pattern
{
    /**
     * Pattern elements
     */
    private List<? extends PatternElement> elements;

    /**
     * Create pattern definition with empty pattern elements list.
     */
    public Pattern()
    {
        elements = new ArrayList<PatternElement>();
    }

    /**
     * Create pattern definition with pattern elements list.
     *
     * @param elements elements of pattern
     */
    public Pattern(List<? extends PatternElement> elements)
    {
        this.elements = elements;
    }

    /**
     * Get pattern elements
     *
     * @return elements list
     */
    public List<? extends PatternElement> getElements()
    {
        return elements;
    }

    /**
     * Set pattern elements
     *
     * @param elements elements list
     */
    public void setElements(List<? extends PatternElement> elements)
    {
        this.elements = elements;
    }

    /**
     * Create pattern for text parsing
     *
     * @param configuration text processor configuration
     * @param createdScopes scopes was created already
     * @param codes         codes
     * @return pattern
     */
    WPattern create(Configuration configuration, Map<Scope, WScope> createdScopes, Map<Code, AbstractCode> codes)
    {
        if (this.elements == null || this.elements.isEmpty())
        {
            throw new IllegalStateException("Pattern elements list can't be empty.");
        }

        List<WPatternElement> elements = new ArrayList<WPatternElement>();
        for(PatternElement element : this.elements)
        {
            elements.add(element.create(configuration, createdScopes, codes));
        }
        return new WPattern(elements);
    }
}

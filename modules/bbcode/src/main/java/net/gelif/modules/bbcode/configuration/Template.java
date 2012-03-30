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

import net.gelif.modules.bbcode.WTemplate;
import net.gelif.modules.bbcode.WTemplateElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Template definition
 *
 * @author admin@gelif.net
 */
public class Template
{
    /**
     * Empty template
     */
    @SuppressWarnings({"unchecked"})
    public static final Template EMPTY = new Template(Collections.EMPTY_LIST);

    private List<? extends TemplateElement> elements;

    /**
     * Create template with empty content
     */
    public Template()
    {
        this.elements = new ArrayList<TemplateElement>();
    }

    /**
     * Create template with elements
     *
     * @param elements template definition elements
     */
    public Template(List<? extends TemplateElement> elements)
    {
        this.elements = elements;
    }

    /**
     * Get template elements
     *
     * @return list of template elements
     */
    public List<? extends TemplateElement> getElements()
    {
        return elements;
    }

    /**
     * Create template from definition
     *
     * @return template
     */
    WTemplate create()
    {
        List<WTemplateElement> elements = new ArrayList<WTemplateElement>();
        if (this.elements != null)
        {
            for(TemplateElement element : this.elements)
            {
                elements.add(element.create());
            }
        }
        return new WTemplate(elements);
    }
}

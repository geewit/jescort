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
import java.util.Collections;
import java.util.List;

/**
 * Code template
 * @author admin@gelif.net
 */
public class WTemplate
{
    /**
     * Empty template
     */
    @SuppressWarnings({"unchecked"})
    public static final WTemplate EMPTY = new WTemplate(Collections.EMPTY_LIST);

    /**
     * Template elemnts
     */
    private final List<? extends WTemplateElement> elements;

    /**
     * Create neq template with elements.
     *
     * @param elements template elements.
     */
    public WTemplate(List<? extends WTemplateElement> elements)
    {
        this.elements = Collections.unmodifiableList(elements);
    }

    /**
     * Append to result string processed text.
     *
     * @param context current context.
     * @throws java.io.IOException if can't append.
     */
    public void generate(Context context) throws IOException
    {
        for(WTemplateElement element : elements)
        {
            context.getTarget().append(element.generate(context));
        }
    }
}

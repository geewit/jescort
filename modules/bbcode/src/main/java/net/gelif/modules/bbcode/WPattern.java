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

import java.util.Collections;
import java.util.List;

/**
 * Represents the pattern
 * @author admin@gelif.net
 */
public class WPattern
{
    /**
     * Pattern elements
     */
    private final List<? extends WPatternElement> elements;

    // Performance optimization
    private final WPatternElement firstElement;

    /**
     * Construct pattern.
     *
     * @param elements pattern elements
     */
    public WPattern(List<? extends WPatternElement> elements)
    {
        this.elements = Collections.unmodifiableList(elements);

        // Performance optimization
        if (!this.elements.isEmpty())
        {
            firstElement = this.elements.get(0);
        } else
        {
            throw new IllegalArgumentException("Parameter \"elements\" can't be empty.");
        }
    }

    public boolean suspicious(Source source)
    {
        return firstElement.isNextIn(source);
    }

    /**
     * Parse context with this pattern
     *
     * @param context current context
     * @return true if next subsequence is valid to this pattern, false others
     */
    public boolean parse(Context context)
    {
        boolean flag = true;
        int start = context.getSource().getOffset();
        int patternSize = elements.size();
        for(int i = 0; i < patternSize && flag; i++)
        {
            WPatternElement current = elements.get(i);
            WPatternElement next;
            if (i < patternSize - 1)
            {
                next = elements.get(i + 1);
            } else
            {
                next = context.getTerminator();
            }
            flag = context.getSource().hasNext() && current.parse(context, next);
        }

        if (!flag)
        {
            context.getSource().setOffset(start);
        }
        return flag;
    }
}

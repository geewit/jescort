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
 * Code with constant string pattern. For basic escaping.
 *
 * @author admin@gelif.net
 */
public class ConstantCode extends AbstractCode
{
    private final String value;
    private final char firstChar;
    private final int valueLength;

    /**
     * Create bb-code with constant pattern
     *
     * @param value    pattern value
     * @param template template
     * @param name     name of code
     * @param priority priority. If priority higher then code be checking early.
     */
    public ConstantCode(String value, WTemplate template, String name, int priority)
    {
        super(template, name, priority);

        this.value = value;
        this.firstChar = value.charAt(0);
        this.valueLength = value.length();
    }

    @Override
    public boolean process(Context context) throws IOException
    {
        context.getSource().incOffset(valueLength);
        template.generate(context);
        return true;
    }

    @Override
    public boolean suspicious(Source source)
    {
        return firstChar == source.current() && source.hasNext(valueLength) && value.contentEquals(source.subTo(valueLength));
    }
}

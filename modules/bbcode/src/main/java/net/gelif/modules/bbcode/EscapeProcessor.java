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

import java.util.Arrays;

/**
 * Class for escape processing. For example, EscapeXmlProcessorFactory use this
 * class for create EscapeXmlProcessor.
 *
 * @author admin@gelif.net
 */
public class EscapeProcessor extends TextProcessorAdapter
{
    /**
     * Escape symbols with replacement.
     */
    private final String[][] escape;

    /**
     * Construct the escape processor with special escape symbols.
     *
     * @param escape escape symbols with replacement. This is a array of array wich
     *               consist of two strings the pattern and the replacement
     */
    public EscapeProcessor(String[][] escape)
    {
        this.escape = escape;
    }

    /**
     * Process the text
     *
     * @param source the sourcetext
     * @return the result of text processing
     * @see TextProcessor#process(CharSequence)
     */
    public CharSequence process(CharSequence source)
    {
        StringBuilder result = new StringBuilder();
        if (source != null && source.length() > 0)
        {
            String stringSource;
            if (source instanceof String)
            {
                stringSource = (String) source;
            } else
            {
                stringSource = source.toString();
            }

            // Array to cache founded indexes of sequences
            int[] indexes = new int[escape.length];
            Arrays.fill(indexes, -1);

            int length = source.length();
            int offset = 0;
            while (offset < length)
            {
                // Find next escape sequence
                int escPosition = -1;
                int escIndex = -1;
                for(int i = 0; i < escape.length; i++)
                {
                    int index;
                    if (indexes[i] < offset)
                    {
                        index = stringSource.indexOf(escape[i][0], offset);
                        indexes[i] = index;
                    } else
                    {
                        index = indexes[i];
                    }

                    if (index >= 0 && (index < escPosition || escPosition < 0))
                    {
                        escPosition = index;
                        escIndex = i;
                    }
                }

                // If escape secuence is found
                if (escPosition >= 0)
                {
                    // replace chars before escape sequence
                    result.append(stringSource, offset, escPosition);

                    // Replace sequence
                    result.append(escape[escIndex][1]);
                    offset = escPosition + escape[escIndex][0].length();
                } else
                {
                    // Put other string to result sequence 
                    result.append(stringSource, offset, length);
                    offset = length;
                }
            }
        }

        return result;
    }
}

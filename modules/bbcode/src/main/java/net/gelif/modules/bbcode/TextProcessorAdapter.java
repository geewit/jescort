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

/**
 * Text Processor adapter implement methods for String, StringBuffer,
 * StringBuilder
 *
 * @author admin@gelif.net
 */
public abstract class TextProcessorAdapter implements TextProcessor
{
    /**
     * Process the text
     *
     * @param source the sourcetext
     * @return the result of text processing
     */
    public String process(String source)
    {
        CharSequence result = process((CharSequence) source);
        if (result instanceof String)
        {
            return (String) result;
        } else
        {
            return result.toString();
        }
    }

    /**
     * Process the text
     *
     * @param source the sourcetext
     * @return the result of text processing
     */
    public StringBuilder process(StringBuilder source)
    {
        CharSequence result = process((CharSequence) source);
        if (result instanceof StringBuilder)
        {
            return (StringBuilder) result;
        } else
        {
            return new StringBuilder(result);
        }
    }

    /**
     * Process the text
     *
     * @param source the sourcetext
     * @return the result of text processing
     */
    public StringBuffer process(StringBuffer source)
    {
        CharSequence result = process((CharSequence) source);
        if (result instanceof StringBuffer)
        {
            return (StringBuffer) result;
        } else
        {
            return new StringBuffer(result);
        }
    }
}

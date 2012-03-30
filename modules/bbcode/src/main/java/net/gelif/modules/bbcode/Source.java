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
 * @author admin@gelif.net
 */
public class Source
{
    private static final int BUFF_SIZE = 4096;

    private final CharSequence text;
    private final int textLength;


    private int offset = 0;
    private char currentChar;


    public Source(CharSequence text)
    {
        this.text = text;
        textLength = text.length();
        updateCurrentChar();
    }

    public int find(String value)
    {
        if (text instanceof String)
        {
            return ((String) text).indexOf(value, offset);
        } else if (text instanceof StringBuilder)
        {
            return ((StringBuilder) text).indexOf(value, offset);
        } else if (text instanceof StringBuffer)
        {
            return ((StringBuffer) text).indexOf(value, offset);
        } else
        {
            int inCharSequence = findInCharSequence(text.subSequence(offset, textLength), value);
            if (inCharSequence >= 0)
            {
                return offset + inCharSequence;
            } else
            {
                return -1;
            }
        }
    }

    /**
     * Find value in character sequence
     *
     * @param sequence character sequence
     * @param value    searched value
     * @return index of value in sequence
     */
    private int findInCharSequence(CharSequence sequence, String value)
    {
        if (value.length() == 0)
        {
            throw new IllegalArgumentException("Argument value can't be empty.");
        }

        final int seqLength = sequence.length();
        final int valLength = value.length();

        if (seqLength < valLength)
        {
            return -1;
        }

        int index;
        int size;

        int nextSize = Math.max(BUFF_SIZE, valLength);
        do
        {
            size = nextSize;
            if (size > seqLength)
            {
                size = seqLength;
            }

            index = sequence.subSequence(0, size).toString().indexOf(value);
            nextSize = 2 * size;
        } while (index <= 0 && size < seqLength);

        return index;
    }

    public char next()
    {
        char c = current();
        offset++;
        updateCurrentChar();
        return c;
    }

    public char current()
    {
        return currentChar;
    }


    public int getOffset()
    {
        return offset;
    }

    /**
     * Increament offset
     */
    public void incOffset()
    {
        offset++;
        updateCurrentChar();
    }

    private void updateCurrentChar()
    {
        if (offset < textLength)
        {
            currentChar = text.charAt(offset);
        }
    }

    public void incOffset(int increment)
    {
        offset += increment;
        updateCurrentChar();
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
        updateCurrentChar();
    }

    public boolean hasNext()
    {
        return offset < textLength;
    }

    public boolean hasNext(int count)
    {
        return (textLength - offset) >= count;
    }

    public int getLength()
    {
        return textLength;
    }

    public CharSequence sub(int end)
    {
        return text.subSequence(getOffset(), end);
    }

    /**
     * Get String from offset to offset+valueLength
     *
     * @param count length of extracted string
     * @return string
     */
    public CharSequence subTo(int count)
    {
        return sub(getOffset() + count);
    }

    public String subString(int start, int end)
    {
        return text.subSequence(start, end).toString();
    }

    /**
     * Get String from offset to end
     *
     * @return string
     */
    public CharSequence subToEnd()
    {
        return sub(textLength);
    }

    public String toString()
    {
        return "net.gelif.modules.bbcode.Source,length:" + String.valueOf(textLength);
    }
}

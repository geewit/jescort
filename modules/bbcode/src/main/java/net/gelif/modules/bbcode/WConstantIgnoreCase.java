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
 * Constant which ignore case
 * @author admin@gelif.net
 */
public class WConstantIgnoreCase implements WPatternElement
{
    /**
     * Constant value
     */
    private final String value;

    /**
     * Length of constant value
     */
    private final int valueLength;

    public WConstantIgnoreCase(String value)
    {
        this.value = value;
        this.valueLength = value.length();
    }

    /**
     * Parse constant
     *
     * @param context    current context
     * @param terminator not used
     * @return true - if next sequence in source equals to this constant value,
     *         false - other
     */
    public boolean parse(Context context, WPatternElement terminator)
    {
        if (isNextIn(context.getSource()))
        {
            context.getSource().incOffset(valueLength);
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * Check equals next sequence in source to this constant
     *
     * @param source source text
     * @return true if next subsequence is equals false other
     */
    public boolean isNextIn(Source source)
    {
        return source.hasNext(valueLength) && value.equalsIgnoreCase(source.subTo(valueLength).toString());
    }

    /**
     * Find this constant.
     *
     * @param source text source
     * @return int
     */
    public int findIn(Source source)
    {
        boolean flag = false;
        int offset;
        for(offset = source.getOffset(); !flag && offset < source.getLength() - valueLength; offset++)
        {
            String str = source.subString(offset, offset + valueLength);
            flag = str.equalsIgnoreCase(value);
        }
        if (flag)
        {
            return offset;
        } else
        {
            return -1;
        }
    }

    /**
     * @return string representation of this object.
     */
    @Override
    public String toString()
    {
        return "constant ic:" + value;
    }
}

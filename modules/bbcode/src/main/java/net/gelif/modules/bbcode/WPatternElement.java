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
 * Pattern element for parse part of bbcode
 * @author admin@gelif.net
 */
public interface WPatternElement
{
    /**
     * Parse element
     *
     * @param context    context
     * @param terminator teminator to stop text process
     * @return true - subsequence is valid to this pattern false - not valid
     */
    public boolean parse(Context context, WPatternElement terminator);

    /**
     * Check next subsequence
     *
     * @param source source text
     * @return true pattern sequence equals with next subsequence false not
     *         equals
     */
    public boolean isNextIn(Source source);

    /**
     * Find constant
     *
     * @param source text source
     * @return constant offset
     */
    public int findIn(Source source);
}

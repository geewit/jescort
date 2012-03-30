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
package net.gelif.test.kernel.core.util;

import static org.junit.Assert.assertEquals;

import net.gelif.kernel.core.util.IntegerIdUtils;
import org.junit.Test;

public class IntegerIdUtilsTest
{
    @Test
    public void string2integer()
    {
        String test10 = "10";
        assertEquals(62, IntegerIdUtils.string2integer(test10));
        String test2 = "1F";
        assertEquals(77, IntegerIdUtils.string2integer(test2));
    }

    @Test
    public void integer2string()
    {
        short test0 = 0;
        assertEquals("0", IntegerIdUtils.integer2string(test0));
        short test333 = 16;
        assertEquals("G", IntegerIdUtils.integer2string(test333));
        short test1 = 62 * 62;
        assertEquals("100", IntegerIdUtils.integer2string(test1));
        short test5 = 103;
        assertEquals("1f", IntegerIdUtils.integer2string(test5));
        short test6 = 1113;
        assertEquals("Hx", IntegerIdUtils.integer2string(test6));
        short test7 = 2113;
        assertEquals("Y5", IntegerIdUtils.integer2string(test7));
        short test8 = 62 * 62 * 2;
        assertEquals("200", IntegerIdUtils.integer2string(test8));
        short test9 = 62 * 62 - 1;
        assertEquals("zz", IntegerIdUtils.integer2string(test9));
    }
}

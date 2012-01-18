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

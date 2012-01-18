package net.gelif.test.kernel.core.util;

import static org.junit.Assert.assertEquals;

import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.gelif.kernel.core.util.EnumMapUtils;
import org.junit.Test;

public class EnumMapUtilsTest
{

    enum TestEnum
    {
        TEST1, TEST2, TEST3, TEST4, TEST5, TEST6
    }

    @Test
    public void toBinary()
    {
        Map<TestEnum, Boolean> enumMap = new ConcurrentHashMap<TestEnum, Boolean>();
        enumMap.put(TestEnum.TEST1, true);
        assertEquals(1, EnumMapUtils.toBinary(enumMap));
        enumMap.put(TestEnum.TEST2, true);
        assertEquals(3, EnumMapUtils.toBinary(enumMap));
    }

    @Test
    public void toEnumMap()
    {
        Map<TestEnum, Boolean> enumMap = EnumMapUtils.toEnumMap(3, TestEnum.class);
        assertEquals(true, enumMap.get(TestEnum.TEST1));
        assertEquals(true, enumMap.get(TestEnum.TEST2));
    }

    @Test
    public void is()
    {
        assertEquals(true, EnumMapUtils.is(TestEnum.TEST1, 1));
        assertEquals(true, EnumMapUtils.is(TestEnum.TEST2, 3));
        assertEquals(false, EnumMapUtils.is(TestEnum.TEST2, 2));
    }

    @Test
    public void hasAll()
    {
        assertEquals(true, EnumMapUtils.hasAll(EnumSet.of(TestEnum.TEST1, TestEnum.TEST2), 3));
        assertEquals(false, EnumMapUtils.hasAll(EnumSet.of(TestEnum.TEST1, TestEnum.TEST2), 4));
        assertEquals(false, EnumMapUtils.hasAll(EnumSet.of(TestEnum.TEST3), 4));
        assertEquals(true, EnumMapUtils.hasAll(EnumSet.of(TestEnum.TEST1, TestEnum.TEST2, TestEnum.TEST3), 7));
        assertEquals(true, EnumMapUtils.hasAll(EnumSet.of(TestEnum.TEST1, TestEnum.TEST2, TestEnum.TEST3, TestEnum.TEST4, TestEnum.TEST5, TestEnum.TEST6), 63));
    }
}

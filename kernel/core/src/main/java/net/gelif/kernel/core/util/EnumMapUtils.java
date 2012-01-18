package net.gelif.kernel.core.util;

import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnumMapUtils
{
    public static <E extends Enum<E>> int toBinary(Map<E, Boolean> enumMap)
    {
        int value = 0;
        if (null != enumMap)
        {
            for(Enum<E> enu : enumMap.keySet())
            {
                if (enumMap.get(enu))
                {
                    value |= 1 << enu.ordinal();
                }
            }
        }
        return value;
    }

    public static <E extends Enum<E>> Map<E, Boolean> toEnumMap(int value, Class<E> clazz)
    {
        Map<E, Boolean> enumMap = new ConcurrentHashMap<E, Boolean>();
        for(E enu : EnumSet.allOf(clazz))
        {
            if ((value & (1 << enu.ordinal())) == (1 << enu.ordinal()))
            {
                enumMap.put(enu, true);
            } else
            {
                enumMap.put(enu, false);
            }
        }
        return enumMap;
    }

    public static <E extends Enum<E>> boolean is(E enu, int value)
    {
        return enu.ordinal() == (enu.ordinal() & value);
    }

    public static <E extends Enum<E>> boolean hasAll(EnumSet<E> enumSet, int value)
    {
        boolean bool = true;
        if (null != enumSet)
        {
            for(Enum<E> enu : enumSet)
            {
                bool &= enu.ordinal() == (enu.ordinal() & value);
            }
        }
        return bool;
    }
}

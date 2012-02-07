package net.gelif.kernel.persistence.eclipselink.mappings.converters;

import java.util.Map;

import net.gelif.kernel.core.util.EnumMapUtils;
import net.gelif.kernel.core.util.GenericsUtils;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

public abstract class EnumMapConverter<E extends Enum<E>> implements Converter
{
    private static final long serialVersionUID = 1L;

    protected Class<E> clazz;

    @SuppressWarnings("unchecked")
    public EnumMapConverter()
    {
        clazz = (Class<E>) GenericsUtils.getSuperClassGenricType(getClass());
    }

    public Object convertDataValueToObjectValue(Object objectValue, Session session)
    {
        Integer intVal = (Integer) objectValue;
        return EnumMapUtils.toEnumMap(intVal, clazz);
    }

    @SuppressWarnings("unchecked")
    public Object convertObjectValueToDataValue(Object dataValue, Session session)
    {
        int value = EnumMapUtils.toBinary((Map<E, Boolean>) dataValue);
        return value;
    }

    public void initialize(DatabaseMapping mapping, Session session)
    {
        return;
    }

    public boolean isMutable()
    {
        return false;
    }
}

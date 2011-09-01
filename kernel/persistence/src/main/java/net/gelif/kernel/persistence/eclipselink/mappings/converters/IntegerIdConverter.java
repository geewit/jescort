package net.gelif.kernel.persistence.eclipselink.mappings.converters;

import net.gelif.kernel.core.util.IntegerIdUtils;
import org.eclipse.persistence.mappings.converters.ObjectTypeConverter;
import org.eclipse.persistence.sessions.Session;

public class IntegerIdConverter extends ObjectTypeConverter
{
    private static final long serialVersionUID = 1L;
    
    public Object convertDataValueToObjectValue(Object dataValue, Session session)
    {
        Integer intVal = (Integer)super.convertDataValueToObjectValue(dataValue, session);
        return IntegerIdUtils.integer2string(intVal);
    }
    
    public Object convertObjectValueToDataValue(Object objectValue, Session session)
    {
        int value = IntegerIdUtils.string2integer((String)objectValue);
        return value;
    }
}

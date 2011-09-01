package net.gelif.kernel.persistence.eclipselink.mappings.converters;

import org.apache.commons.lang.LocaleUtils;
import org.eclipse.persistence.mappings.converters.ObjectTypeConverter;
import org.eclipse.persistence.sessions.Session;

public class LocaleConverter extends ObjectTypeConverter
{
    private static final long serialVersionUID = 1L;
    
    public Object convertDataValueToObjectValue(Object dataValue, Session session)
    {
        String str = (String)super.convertDataValueToObjectValue(dataValue, session);
        return LocaleUtils.toLocale(str);
    }
    
    public Object convertObjectValueToDataValue(Object objectValue, Session session)
    {
        String str = objectValue.toString();
        return str;
    }
}

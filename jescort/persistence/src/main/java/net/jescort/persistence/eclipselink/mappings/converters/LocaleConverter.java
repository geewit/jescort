package net.jescort.persistence.eclipselink.mappings.converters;

import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-1-29
 * Time: 下午11:03
 */
public class LocaleConverter implements Converter
{
    private static final long serialVersionUID = 1L;

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session)
    {
        Locale locale = (Locale) objectValue;
        return locale.toString();
    }

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session)
    {
        String strVal = (String) dataValue;
        Locale locale = LocaleUtils.toLocale(strVal);
        return locale;
    }

    @Override
    public boolean isMutable()
    {
        return false;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session)
    {

    }
}

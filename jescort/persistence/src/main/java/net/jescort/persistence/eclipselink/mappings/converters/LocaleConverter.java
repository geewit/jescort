package net.jescort.persistence.eclipselink.mappings.converters;

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
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session)
    {
        String strVal = (String) dataValue;
        String[] strVals = StringUtils.split(strVal, "_");
        Locale locale = new Locale(strVals[0], strVals[1]);
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

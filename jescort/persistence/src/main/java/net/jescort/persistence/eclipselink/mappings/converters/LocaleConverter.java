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
package net.jescort.persistence.eclipselink.mappings.converters;

import org.apache.commons.lang.LocaleUtils;
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

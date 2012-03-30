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
package net.gelif.kernel.persistence.eclipselink.mappings.converters;

import org.apache.commons.lang.LocaleUtils;
import org.eclipse.persistence.mappings.converters.ObjectTypeConverter;
import org.eclipse.persistence.sessions.Session;

public class LocaleConverter extends ObjectTypeConverter
{
    private static final long serialVersionUID = 1L;

    public Object convertDataValueToObjectValue(Object dataValue, Session session)
    {
        String str = (String) super.convertDataValueToObjectValue(dataValue, session);
        return LocaleUtils.toLocale(str);
    }

    public Object convertObjectValueToDataValue(Object objectValue, Session session)
    {
        String str = objectValue.toString();
        return str;
    }
}

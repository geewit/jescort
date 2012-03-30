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

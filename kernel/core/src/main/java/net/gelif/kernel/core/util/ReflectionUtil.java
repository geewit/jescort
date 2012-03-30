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
package net.gelif.kernel.core.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionUtil
{
    private static final Log logger = LogFactory.getLog(ReflectionUtil.class);

    public static void setFieldValue(final Object target, final String fname, final Class<?> ftype, final Object fvalue)
    {
        if (null == target || StringUtils.isBlank(fname) || (null != fvalue && !ftype.isAssignableFrom(fvalue.getClass())))
        {
            return;
        }
        final Class<?> clazz = target.getClass();
        try
        {
            final Method method = clazz.getDeclaredMethod(String.format("set%s", StringUtils.capitalize(fname)), ftype);
            if (!Modifier.isPublic(method.getModifiers()))
            {
                method.setAccessible(true);
            }
            method.invoke(target, fvalue);

        } catch (final Exception me)
        {
            try
            {
                final Field field = clazz.getDeclaredField(fname);
                if (!Modifier.isPublic(field.getModifiers()))
                {
                    field.setAccessible(true);
                }
                field.set(target, fvalue);
            } catch (final Exception fe)
            {
            }
        }
    }

    public static boolean isAutowire(Autowire autowire)
    {
        return autowire.equals(Autowire.BY_NAME) || autowire.equals(Autowire.BY_TYPE);
    }

    public static boolean isRequired(Field method)
    {
        return method.getAnnotation(Required.class) != null;
    }

    public static boolean isRequired(Method method)
    {
        return method.getAnnotation(Required.class) != null;
    }

    public static String getWriteMethodName(Field field)
    {
        return "set" + StringUtils.capitalize(field.getName());
    }

    public static boolean isWriteMethod(Method method)
    {
        return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
    }

    public static boolean isStatic(Field field)
    {
        return (field.getModifiers() & Modifier.STATIC) != 0;
    }

    public static boolean isStatic(Method method)
    {
        return (method.getModifiers() & Modifier.STATIC) != 0;
    }

    public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes, final Object[] parameters) throws InvocationTargetException
    {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null)
        {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }

        method.setAccessible(true);

        try
        {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException e)
        {
            logger.error("impossiblly to throw out:{}", e);
        }

        return null;
    }

    protected static Field getDeclaredField(final Object object, final String fieldName)
    {
        Assert.notNull(object, "object can not be null");
        Assert.hasText(fieldName, "fieldName");
        for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e)
            {
                // Field is not defined here
            }
        }
        return null;
    }

    protected static void makeAccessible(final Field field)
    {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()))
        {
            field.setAccessible(true);
        }
    }

    protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes)
    {
        Assert.notNull(object, "object can not be null");

        for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e)
            {
                // Method is not defined here
            }
        }
        return null;
    }

    public static Class<?> getSuperClassGenricType(final Class<?> clazz)
    {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index)
    {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType))
        {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0)
        {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class<?>))
        {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return params[index].getClass();
    }

    public static List<Object> fetchElementPropertyToList(final Collection<Object> collection, final String propertyName)
    {
        List<Object> list = new ArrayList<Object>();

        try
        {
            for(Object obj : collection)
            {
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (Exception e)
        {
            convertToUncheckedException(e);
        }

        return list;
    }

    public static String fetchElementPropertyToString(final Collection<Object> collection, final String propertyName, final String separator)
    {
        List<Object> list = fetchElementPropertyToList(collection, propertyName);
        return StringUtils.join(list, separator);
    }

    public static void convertToUncheckedException(Exception e) throws IllegalArgumentException
    {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException || e instanceof NoSuchMethodException)
        {
            throw new IllegalArgumentException("Refelction Exception.", e);
        } else
        {
            throw new IllegalArgumentException(e);
        }
    }
}

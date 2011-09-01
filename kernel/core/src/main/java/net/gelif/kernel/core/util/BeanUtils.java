package net.gelif.kernel.core.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * extend Spring BeanUtils
 */
public class BeanUtils extends org.springframework.beans.BeanUtils
{
    private static final Log logger = LogFactory.getLog(BeanUtils.class);
    
    private BeanUtils()
    {
    }
    
    static public Field getDeclaredField(final Object object, final String propertyName) throws NoSuchFieldException
    {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        return getDeclaredField(object.getClass(), propertyName);
    }
    
    static public Field getDeclaredField(final Class<?> clazz, final String propertyName) throws NoSuchFieldException
    {
        Assert.notNull(clazz);
        Assert.hasText(propertyName);
        for(Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                return superClass.getDeclaredField(propertyName);
            }
            catch(final NoSuchFieldException e)
            {
                logger.warn(e.toString());
            }
        }
        throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
    }
    
    static public Object forceGetProperty(final Object object, final String propertyName) throws NoSuchFieldException
    {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        final Field field = getDeclaredField(object, propertyName);
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        Object result = null;
        try
        {
            result = field.get(object);
        }
        catch(final IllegalAccessException e)
        {
            logger.info("error wont' happen");
        }
        field.setAccessible(accessible);
        return result;
    }
    
    static public void forceSetProperty(final Object object, final String propertyName, final Object newValue) throws NoSuchFieldException
    {
        Assert.notNull(object);
        Assert.hasText(propertyName);
        final Field field = getDeclaredField(object, propertyName);
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try
        {
            field.set(object, newValue);
        }
        catch(final IllegalAccessException e)
        {
            logger.info("Error won't happen");
        }
        field.setAccessible(accessible);
    }
    
    static public Object invokePrivateMethod(final Object object, final String methodName, final Object... params) throws NoSuchMethodException
    {
        Assert.notNull(object);
        Assert.hasText(methodName);
        final Class<?>[] types = new Class[params.length];
        for(int i = 0; i < params.length; i++)
        {
            types[i] = params[i].getClass();
        }
        final Class<?> clazz = object.getClass();
        Method method = null;
        for(Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                method = superClass.getDeclaredMethod(methodName, types);
                break;
            }
            catch(final NoSuchMethodException e)
            {
                //
            }
        }
        if(null == method)
        {
            throw new NoSuchMethodException("No Such Method:" + clazz.getSimpleName() + methodName + params);
        }
        final boolean accessible = method.isAccessible();
        method.setAccessible(true);
        Object result = null;
        try
        {
            result = method.invoke(object, params);
        }
        catch(final Exception e)
        {
            ReflectionUtils.handleReflectionException(e);
        }
        method.setAccessible(accessible);
        return result;
    }
    
    static public List<Field> getFieldsByType(final Object object, final Class<?> type)
    {
        final List<Field> list = new ArrayList<Field>();
        final Field[] fields = object.getClass().getDeclaredFields();
        for(final Field field : fields)
        {
            if(field.getType().isAssignableFrom(type))
            {
                list.add(field);
            }
        }
        return list;
    }
    
    public static Class<?> getPropertyType(final Class<?> type, final String name) throws SecurityException, NoSuchFieldException
    {
        return getDeclaredField(type, name).getType();
    }
    
    public static String getGetterName(final Class<?> type, final String fieldName)
    {
        Assert.notNull(type, "Type required");
        Assert.hasText(fieldName, "FieldName required");
        if("boolean".equals(type.getName()))
        {
            return "is" + StringUtils.capitalize(fieldName);
        }
        else
        {
            return "findOne" + StringUtils.capitalize(fieldName);
        }
    }
    
    public static Method getGetterMethod(final Class<?> type, final String fieldName)
    {
        try
        {
            return type.getMethod(getGetterName(type, fieldName));
        }
        catch(final NoSuchMethodException e)
        {
            logger.error(e.toString(), e);
        }
        return null;
    }
    
    public static void copyProperties(Object source, Object target) throws BeansException
    {
        copyProperties(source, target, null, null);
    }
    
    public static void copyProperties(Object source, Object target, Class<?> editable) throws BeansException
    {
        copyProperties(source, target, editable, null);
    }
    
    public static void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException
    {
        copyProperties(source, target, null, ignoreProperties);
    }
    
    private static void copyProperties(final Object source, final Object target, final Class<?> editable, final String[] ignoreProperties) throws BeansException
    {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        
        Class<?> actualEditable = target.getClass();
        if(null != editable)
        {
            if(!editable.isInstance(target))
            {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        final PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        final List<String> ignoreList = (null != ignoreProperties) ? Arrays.asList(ignoreProperties) : null;
        
        for(final PropertyDescriptor targetPd : targetPds)
        {
            if(null != targetPd.getWriteMethod() && (null == ignoreProperties || (!ignoreList.contains(targetPd.getName()))))
            {
                final PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if(null != sourcePd && null != sourcePd.getReadMethod())
                {
                    try
                    {
                        final Method readMethod = sourcePd.getReadMethod();
                        if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers()))
                        {
                            readMethod.setAccessible(true);
                        }
                        final Object value = readMethod.invoke(source, new Object[0]);
                        final Method writeMethod = targetPd.getWriteMethod();
                        if(!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
                        {
                            writeMethod.setAccessible(true);
                        }
                        if(null != value && StringUtils.isNotBlank(value.toString()))
                        {
                            writeMethod.invoke(target, new Object[]{value});
                        }
                        else
                        {
                            continue;
                        }
                    }
                    catch(final Throwable ex)
                    {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }
}

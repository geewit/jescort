package net.gelif.kernel.core.util;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.Metamodel;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.JpaPersistableEntityInformation;

/**
 * Created by IntelliJ IDEA. User: admin@gelif.net Date: 11-7-24 Time: 上午10:57
 */
public class JpaEntityUtils
{
    public static <T extends Persistable<PK>, PK extends Serializable> JpaEntityInformation<T, PK> getMetadata(Class<T> domainClass, EntityManager entityManager)
    {
        Metamodel metamodel = entityManager.getMetamodel();

        if (Persistable.class.isAssignableFrom(domainClass))
        {
            return new JpaPersistableEntityInformation<T, PK>(domainClass, metamodel);
        } else
        {
            try
            {
                return new JpaMetamodelEntityInformation<T, PK>(domainClass, metamodel);
            } catch (IllegalArgumentException e)
            {
                return null;
            }
        }
    }
}

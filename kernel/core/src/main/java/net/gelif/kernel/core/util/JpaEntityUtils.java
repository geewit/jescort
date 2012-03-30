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

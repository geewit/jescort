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
package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.IdGenerator;
import net.jescort.domain.enums.IdName;
import net.jescort.persistence.dao.IdGeneratorDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-14
 * Time: 下午9:26
 */
@Repository("idGeneratorDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class IdGeneratorJpaDao extends GenericJpaDao<IdGenerator, String> implements IdGeneratorDao
{
    public Integer newId(IdName idName) throws DataAccessException
    {
        IdGenerator idGenerator = entityManager.find(IdGenerator.class, idName.getIdName());
        Integer id = idGenerator.getValue();
        idGenerator.setValue(id + 1);
        entityManager.merge(idGenerator);
        return id;
    }
}
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
import net.jescort.domain.user.Role;
import net.jescort.persistence.dao.RoleDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository("roleDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class RoleJpaDao extends GenericJpaDao<Role, Integer> implements RoleDao
{
    @SuppressWarnings("unchecked")
    @Override
    public List<String> findByUsername(String username)
    {
        Query query = entityManager.createNativeQuery("SELECT roles.authority FROM group_role_map " +
                "INNER JOIN groups ON group_role_map.group_id = groups.id " +
                "INNER JOIN roles ON group_role_map.role_id = roles.id " +
                "INNER JOIN user_group_map ON user_group_map.group_id = groups.id " +
                "INNER JOIN users ON user_group_map.user_id = users.id " +
                "WHERE users.username = ?").setParameter(1, username);
        return (List<String>)query.getResultList();
    }
}

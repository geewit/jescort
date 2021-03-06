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
import net.jescort.domain.forum.Forum;
import net.jescort.persistence.dao.ForumDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository("forumDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ForumJpaDao extends GenericJpaDao<Forum, Integer> implements ForumDao
{
    @Override
    public void increaseTopics(Integer forumId)
    {
        Query query = entityManager.createNativeQuery("UPDATE forums set topics = topics + 1 where id = ?");
        query.setParameter(1, forumId);
        query.executeUpdate();
    }

    @Override
    public void increaseReplys(Integer forumId)
    {
        Query query = entityManager.createNativeQuery("UPDATE forums set replies = replies + 1 where id = ?");
        query.setParameter(1, forumId);
        query.executeUpdate();
    }
}

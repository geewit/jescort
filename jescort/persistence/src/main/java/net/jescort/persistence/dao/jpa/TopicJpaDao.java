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

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Topic;
import net.jescort.persistence.dao.TopicDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository("topicDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TopicJpaDao extends GenericJpaDao<Topic, Integer> implements TopicDao
{
    //private transient final static Log logger = LogFactory.getLog(TopicJpaDao.class);

    @SuppressWarnings("unchecked")
    public List<Topic> findByCreater(int creater)
    {
        return entityManager.createQuery("SELECT t FROM Topic t WHERE t.rootPost.poster.id = :creater").setParameter("creater", creater).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Topic> findByCreater(int creater, int offset, int limit)
    {
        Query query = entityManager.createQuery("SELECT t FROM Topic t WHERE t.rootPost.poster.id = :creater");
        query.setParameter("creater", creater);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public Topic findNextTopicInForum(int forumId, int currentTopicId)
    {
        Query query = entityManager.createQuery("SELECT t FROM Topic t WHERE (t.forumId = :forumId) AND (t.id > :currentTopicId)");
        query.setParameter("forumId", forumId);
        query.setParameter("currentTopicId", currentTopicId);
        query.setFirstResult(0);
        query.setMaxResults(1);
        try
        {
            return (Topic) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    public long countByForum(int forumId)
    {
        return (Long) entityManager.createQuery("SELECT count(t.id) FROM Topic t WHERE t.forumId = :forumId").setParameter("forumId", forumId).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Topic> findByForum(int forumId, Pageable pageable) throws DataAccessException
    {
        String jpql = "SELECT t FROM Topic t WHERE t.forumId = :forumId";
        Query query = entityManager.createQuery(jpql);

        query.setParameter("forumId", forumId);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Topic> topics = query.getResultList();
        return topics;
    }

    @Override
    public void increaseViews(Integer topicId)
    {
        Query query = entityManager.createNativeQuery("UPDATE topics set views = views + 1 WHERE id = ?");
        query.setParameter(1, topicId);
        query.executeUpdate();
    }

    @Override
    public void replyTopic(Integer topicId, Integer lastPostId)
    {
        Query query = entityManager.createNativeQuery("UPDATE topics SET last_post_id = ?, replies = replies + 1 WHERE id = ?");
        query.setParameter(1, lastPostId);
        query.setParameter(2, topicId);
        query.executeUpdate();
    }
}

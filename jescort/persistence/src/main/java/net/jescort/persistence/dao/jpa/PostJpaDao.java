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
import javax.persistence.Query;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Post;
import net.jescort.persistence.dao.PostDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository("postDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PostJpaDao extends GenericJpaDao<Post, Integer> implements PostDao
{
    public long countByTopicId(int topicId)
    {
        return (Long) entityManager.createQuery("select count(t.id) from Post t where t.topic.id = :topicId").setParameter("topicId", topicId).getSingleResult();
    }

    @Override
    public long rownumberPostsByTopicIdAndPostId(int topicId, int postId)
    {
        return (Long) entityManager.createNativeQuery("SELECT COUNT(p.id) from posts p WHERE p.topic_id = ? AND p.id < ?").setParameter(1, topicId).setParameter(2, postId).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByTopicId(int topicId)
    {
        return entityManager.createQuery("select t from Post t where t.topic.id = :topicId order by t.createdate").setParameter("topicId", topicId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByTopicId(int topicId, Pageable pageable)
    {
        String jpql = "select t from Post t where t.topic.id = :topicId order by t.createdate";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("topicId", topicId);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByUserId(String userId)
    {
        return entityManager.createQuery("select t from Post t where t.poster.id = :userId order by t.createdate").setParameter("userId", userId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByUserId(String userId, int offset, int limit)
    {
        Query query = entityManager.createQuery("select t from Post t where t.poster.id = :userId");
        query.setParameter("userId", userId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public void increaseEdits(Integer id)
    {
        Query query = entityManager.createNativeQuery("UPDATE posts set edits = edits + 1 where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }
}

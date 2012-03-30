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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Message;
import net.jescort.domain.user.User;
import net.jescort.persistence.dao.MessageDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-14
 * Time: 下午8:26
 */
@Repository("messageDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MessageJpaDao extends GenericJpaDao<Message, Integer> implements MessageDao
{
    @Override
    public long countBySenderId(String senderId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> sender = message.join("sender");
        criteriaQuery.select(criteriaBuilder.count(message)).where(criteriaBuilder.equal(sender.get("id"), senderId));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Message> findBySenderId(String senderId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        criteriaQuery.where(criteriaBuilder.equal(message.get("id"), senderId));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findBySenderId(String senderId, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> sender = message.join("sender");
        criteriaQuery.where(criteriaBuilder.equal(sender.get("id"), senderId));
        Query query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Message> findBySenderId(String senderId, int offset, int limit)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> sender = message.join("sender");
        criteriaQuery.where(criteriaBuilder.equal(sender.get("id"), senderId));
        Query query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public long countByRecipientId(String recipientId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> recipient = message.join("recipients");
        criteriaQuery.select(criteriaBuilder.count(message)).where(criteriaBuilder.equal(recipient.get("id"), recipientId));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findByRecipientId(String recipientId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> recipient = message.join("recipients");
        criteriaQuery.select(message).where(criteriaBuilder.equal(recipient.get("id"), recipientId));
        TypedQuery<Message> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findByRecipientId(String recipientId, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> recipient = message.join("recipients");
        criteriaQuery.select(message).where(criteriaBuilder.equal(recipient.get("id"), recipientId));
        TypedQuery<Message> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findByRecipientId(String recipientId, int offset, int limit)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> recipient = message.join("recipients");
        criteriaQuery.select(message).where(criteriaBuilder.equal(recipient.get("id"), recipientId));
        TypedQuery<Message> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}

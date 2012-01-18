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


@Repository("messageDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MessageJpaDao extends GenericJpaDao<Message, Integer> implements MessageDao
{
    @Override
    public long countBySenderId(int senderId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Message> message = criteriaQuery.from(Message.class);
        Path<User> sender = message.join("sender");
        criteriaQuery.select(criteriaBuilder.count(message)).where(criteriaBuilder.equal(sender.get("id"), senderId));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Message> findBySenderId(int senderId)
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
    public List<Message> findBySenderId(int senderId, Pageable pageable)
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
    public List<Message> findBySenderId(int senderId, int offset, int limit)
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
    public long countByRecipientId(int recipientId)
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
    public List<Message> findByRecipientId(int recipientId)
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
    public List<Message> findByRecipientId(int recipientId, Pageable pageable)
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
    public List<Message> findByRecipientId(int recipientId, int offset, int limit)
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

package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Attachment;
import net.jescort.persistence.dao.AttachmentDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository("attachmentDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AttachmentJpaDao extends GenericJpaDao<Attachment, Integer> implements AttachmentDao
{
    @SuppressWarnings("unchecked")
    @Override
    public List<Attachment> findByUser(String userId)
    {
        return entityManager.createQuery("select a from Attachment a where a.owner.id = :userId").setParameter("userId", userId).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Attachment> findByUser(String userId, int offset, int limit)
    {
        Query query = entityManager.createQuery("select a from Attachment a where a.owner.id = :userId");
        query.setParameter("userId", userId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Attachment> findByUser(String userId, Pageable pageable)
    {
        Query query = entityManager.createQuery("select a from Attachment a where a.owner.id = :userId");
        query.setParameter("userId", userId);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @Override
    public long countByUserId(String userId)
    {
        return (Long) entityManager.createQuery("select count(a.id) from Attachment a where a.owner.id = :userId").setParameter("userId", userId).getSingleResult();
    }
}

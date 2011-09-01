package net.jescort.persistence.dao.jpa;

import java.util.List;
import javax.persistence.Query;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Draft;
import net.jescort.persistence.dao.DraftDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("draftDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DraftJpaDao extends GenericJpaDao<Draft, Integer> implements DraftDao
{
    @SuppressWarnings("unchecked")
    public List<Draft> findByUserId(int userId)
    {
        return entityManager.createQuery("SELECT * FROM Draft d WHERE d. = ?").setParameter(1, userId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Draft> findByUserId(int userId, int offset, int limit)
    {
        Query query = entityManager.createNativeQuery("SELECT * FROM drafts WHERE user_id = ?");
        query.setParameter(1, userId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}

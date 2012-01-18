package net.jescort.persistence.dao.jpa;

import java.util.List;
import javax.persistence.Query;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.enums.BanType;
import net.jescort.domain.forum.Banned;
import net.jescort.persistence.dao.BannedDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("bannedDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class BannedJpaDao extends GenericJpaDao<Banned, Integer> implements BannedDao
{
    @SuppressWarnings("unchecked")
    public List<Banned> findByKey(BanType key)
    {
        return entityManager.createQuery("SELECT t FROM Banned t WHERE key = :key").setParameter("key", key).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Banned> findByKey(BanType key, int offset, int limit)
    {
        Query query = entityManager.createQuery("SELECT t FROM Banned t WHERE key = :key");
        query.setParameter("key", key);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}

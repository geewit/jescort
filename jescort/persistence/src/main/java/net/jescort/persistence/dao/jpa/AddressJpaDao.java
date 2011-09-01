package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.user.Address;
import net.jescort.persistence.dao.AddressDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;

@Repository("addressDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AddressJpaDao extends GenericJpaDao<Address, Integer> implements AddressDao
{
    @SuppressWarnings("unchecked")
    public List<Address> findByUserId(int userId)
    {
        Query query = entityManager.createQuery("SELECT t FROM Address t WHERE t.userId = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Address> findByUserId(int userId, int offset, int limit)
    {
        Query query = entityManager.createQuery("SELECT t FROM Address t WHERE t.userId = :userId");
        query.setParameter("userId", userId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}

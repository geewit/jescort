package net.jescort.persistence.dao.jpa;

import java.util.List;
import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.Location;
import net.jescort.persistence.dao.LocationDao;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("locationDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LocationJpaDao extends GenericJpaDao<Location, Integer> implements LocationDao
{
    @SuppressWarnings("unchecked")
    @Override
    public List<Location> getByFirstLevel()
    {
        return entityManager.createQuery("select l from Location l where l.level = :level").setParameter("level", NumberUtils.INTEGER_ONE).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Location> getChildern(Integer id)
    {
        return entityManager.createNativeQuery("SELECT * from locations WHERE parent_id = ?").setParameter("id", id).getResultList();
    }
}

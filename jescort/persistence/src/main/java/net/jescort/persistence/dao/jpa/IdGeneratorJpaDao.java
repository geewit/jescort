package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.IdGenerator;
import net.jescort.domain.enumerator.IdName;
import net.jescort.persistence.dao.IdGeneratorDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Repository("idGeneratorDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class IdGeneratorJpaDao extends GenericJpaDao<IdGenerator, Integer> implements IdGeneratorDao
{
    public Integer newId(IdName idName) throws DataAccessException
    {
        IdGenerator idGenerator = entityManager.find(IdGenerator.class, idName.getIdName());
        Integer id = idGenerator.getValue();
        idGenerator.setValue(id + 1);
        entityManager.merge(idGenerator);
        return id;
    }
}

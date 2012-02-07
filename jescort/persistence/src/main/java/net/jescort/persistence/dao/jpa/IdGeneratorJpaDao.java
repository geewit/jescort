package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.IdGenerator;
import net.jescort.domain.enums.IdName;
import net.jescort.persistence.dao.IdGeneratorDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Repository("idGeneratorDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class IdGeneratorJpaDao extends GenericJpaDao<IdGenerator, String> implements IdGeneratorDao
{
    //private transient final static Log logger = LogFactory.getLog(IdGeneratorJpaDao.class);

    public Integer newId(IdName idName) throws DataAccessException
    {;
        IdGenerator idGenerator = entityManager.find(IdGenerator.class, idName.getIdName());
        Integer id = idGenerator.getValue();
        idGenerator.setValue(id + 1);
        entityManager.merge(idGenerator);
        return id;
    }
}

package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Dirtyword;
import net.jescort.persistence.dao.DirtywordDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("dirtywordDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DirtywordJpaDao extends GenericJpaDao<Dirtyword, Integer> implements DirtywordDao
{
}

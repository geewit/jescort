package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.user.Role;
import net.jescort.persistence.dao.RoleDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("roleDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class RoleJpaDao extends GenericJpaDao<Role, Integer> implements RoleDao
{
}

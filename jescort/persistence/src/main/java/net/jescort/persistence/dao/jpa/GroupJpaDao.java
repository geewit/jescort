package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.user.Group;
import net.jescort.persistence.dao.GroupDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("groupDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class GroupJpaDao extends GenericJpaDao<Group, Integer> implements GroupDao
{
}

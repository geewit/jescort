package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.user.Role;
import net.jescort.persistence.dao.RoleDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository("roleDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class RoleJpaDao extends GenericJpaDao<Role, Integer> implements RoleDao
{
    @Override
    public List<Role> findByUsername(String username)
    {
        Query query = entityManager.createNativeQuery("SELECT roles.authority FROM group_role_map " +
                "INNER JOIN groups ON group_role_map.group_id = groups.id " +
                "INNER JOIN roles ON group_role_map.role_id = roles.id " +
                "INNER JOIN user_group_map ON user_group_map.group_id = groups.id " +
                "INNER JOIN users ON user_group_map.user_id = users.id " +
                "WHERE users.username = ?").setParameter(1, username);
        return (List<Role>)query.getResultList();
    }
}

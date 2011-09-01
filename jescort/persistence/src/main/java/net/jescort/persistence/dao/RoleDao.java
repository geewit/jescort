package net.jescort.persistence.dao;


import net.jescort.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer>
{
}

package net.jescort.persistence.dao;


import net.jescort.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Integer>
{
    public List<String> findByUsername(String username);
}

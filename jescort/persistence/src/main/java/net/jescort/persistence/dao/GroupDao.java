package net.jescort.persistence.dao;

import net.jescort.domain.user.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<Group, Integer>
{
}

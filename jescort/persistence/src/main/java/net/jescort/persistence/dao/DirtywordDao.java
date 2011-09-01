package net.jescort.persistence.dao;


import net.jescort.domain.forum.Dirtyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirtywordDao extends JpaRepository<Dirtyword, Integer>
{
    
}

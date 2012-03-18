package net.jescort.persistence.dao;

import net.jescort.domain.forum.Draft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DraftDao extends JpaRepository<Draft, Integer>
{
    public List<Draft> findByUserId(String userId);

    public List<Draft> findByUserId(String userId, int offset, int limit);
}

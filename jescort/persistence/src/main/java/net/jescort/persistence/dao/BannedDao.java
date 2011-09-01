package net.jescort.persistence.dao;

import net.jescort.domain.enumerator.BanType;
import net.jescort.domain.forum.Banned;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BannedDao extends JpaRepository<Banned, Integer>
{
    public List<Banned> findByKey(BanType key);
    public List<Banned> findByKey(BanType key, int offset, int limit);
}

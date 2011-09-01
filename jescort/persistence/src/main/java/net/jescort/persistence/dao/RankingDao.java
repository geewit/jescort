package net.jescort.persistence.dao;

import net.jescort.domain.forum.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingDao extends JpaRepository<Ranking, Integer>
{
    public Ranking findByScore(int score);
}

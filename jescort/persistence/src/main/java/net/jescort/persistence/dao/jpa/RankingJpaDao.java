package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Ranking;
import net.jescort.persistence.dao.RankingDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;


@Repository("rankingDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class RankingJpaDao extends GenericJpaDao<Ranking, Integer> implements RankingDao
{
    public Ranking findByScore(int score)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ranking> criteriaQuery = criteriaBuilder.createQuery(Ranking.class);
        Root<Ranking> rankingRoot = criteriaQuery.from(Ranking.class);
        Path<Integer> minScore = rankingRoot.get("minScore");
        Path<Integer> maxScore = rankingRoot.get("maxScore");
        criteriaQuery.select(rankingRoot).where(criteriaBuilder.le(minScore, score), criteriaBuilder.ge(maxScore, score));
        TypedQuery<Ranking> typedQuery = entityManager.createQuery(criteriaQuery);
        Ranking ranking = typedQuery.getSingleResult();
        return ranking;
    }
}

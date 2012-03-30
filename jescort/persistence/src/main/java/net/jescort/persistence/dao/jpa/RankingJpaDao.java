/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
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

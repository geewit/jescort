package net.jescort.persistence.dao.jpa;

import java.util.List;
import javax.persistence.Query;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Topic;
import net.jescort.persistence.dao.TopicDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository("topicDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TopicJpaDao extends GenericJpaDao<Topic, Integer> implements TopicDao
{
    @SuppressWarnings("unchecked")
    public List<Topic> findByCreater(int creater)
    {
        return entityManager.createQuery("select t from Topic t where t.rootPost.poster.id = :creater").setParameter("creater", creater).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Topic> findByCreater(int creater, int offset, int limit)
    {
        Query query = entityManager.createQuery("select t from Topic t where t.rootPost.poster.id = :creater");
        query.setParameter("creater", creater);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public long countByForum(int forumId)
    {
        return (Long) entityManager.createQuery("select count(t.id) from Topic t where t.forumId = :forumId").setParameter("forumId", forumId).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Topic> findByForum(int forumId, Pageable pageable) throws DataAccessException
    {
        String jpql = "select t from Topic t where t.forumId = :forumId";
        Query query = entityManager.createQuery(jpql);

        query.setParameter("forumId", forumId);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @Override
    public void increaseViews(Integer topicId)
    {
        Query query = entityManager.createNativeQuery("UPDATE topics set views = views + 1 where id = ?");
        query.setParameter(1, topicId);
        query.executeUpdate();
    }

    @Override
    public void replyTopic(Integer topicId, Integer lastPostId)
    {
        Query query = entityManager.createNativeQuery("UPDATE topics SET last_post_id = ?, replies = replies + 1 WHERE id = ?");
        query.setParameter(1, lastPostId);
        query.setParameter(2, topicId);
        query.executeUpdate();
    }
}

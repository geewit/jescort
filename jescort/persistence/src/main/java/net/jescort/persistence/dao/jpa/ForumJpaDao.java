package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Forum;
import net.jescort.persistence.dao.ForumDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository("forumDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ForumJpaDao extends GenericJpaDao<Forum, Integer> implements ForumDao
{
    @Override
    public void increaseTopics(Integer forumId)
    {
        Query query = entityManager.createNativeQuery("UPDATE forums set topics = topics + 1 where id = ?");
        query.setParameter(1, forumId);
        query.executeUpdate();
    }

    @Override
    public void increaseReplys(Integer forumId)
    {
        Query query = entityManager.createNativeQuery("UPDATE forums set replies = replies + 1 where id = ?");
        query.setParameter(1, forumId);
        query.executeUpdate();
    }
}

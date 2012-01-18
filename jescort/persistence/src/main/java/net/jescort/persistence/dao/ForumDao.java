package net.jescort.persistence.dao;


import net.jescort.domain.forum.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumDao extends JpaRepository<Forum, Integer>
{
    public void increaseTopics(Integer forumId);

    public void increaseReplys(Integer forumId);
}

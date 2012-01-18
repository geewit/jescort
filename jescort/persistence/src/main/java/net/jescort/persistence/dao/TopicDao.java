package net.jescort.persistence.dao;

import net.jescort.domain.forum.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicDao extends JpaRepository<Topic, Integer>
{
    public List<Topic> findByCreater(int creater);

    public List<Topic> findByCreater(int creater, int offset, int limit);

    public long countByForum(int forumId);

    public List<Topic> findByForum(int forumId, Pageable pageable);

    public void increaseViews(Integer topicId);

    public void replyTopic(Integer topicId, Integer lastPostId);
}

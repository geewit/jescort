package net.jescort.persistence.dao;

import net.jescort.domain.forum.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Integer>
{
    public long countByTopicId(int topicId);

    public long rownumberPostsByTopicIdAndPostId(int topicId, int postId);

    public List<Post> findByTopicId(int topicId);

    public List<Post> findByTopicId(int topicId, Pageable pageable);

    public List<Post> findByUserId(String userId);

    public List<Post> findByUserId(String userId, int offset, int limit);

    public void increaseEdits(Integer id);
}

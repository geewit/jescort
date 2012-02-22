package net.jescort.persistence.dao;


import net.jescort.domain.forum.Attachment;
import net.jescort.domain.forum.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentDao extends JpaRepository<Attachment, Integer>
{
    public List<Attachment> findByUser(int userId);

    public List<Attachment> findByUser(int userId, int offset, int limit);

    public List<Attachment> findByUser(int userId, Pageable pageable);

    public long countByUserId(int userId);
}

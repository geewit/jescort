package net.jescort.persistence.dao;


import net.jescort.domain.forum.Attachment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentDao extends JpaRepository<Attachment, Integer>
{
    public List<Attachment> findByUser(String userId);

    public List<Attachment> findByUser(String userId, int offset, int limit);

    public List<Attachment> findByUser(String userId, Pageable pageable);

    public long countByUserId(String userId);
}

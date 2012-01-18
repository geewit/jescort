package net.jescort.persistence.dao;


import net.jescort.domain.forum.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentDao extends JpaRepository<Attachment, Integer>
{

}

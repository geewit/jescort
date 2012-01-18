package net.jescort.persistence.dao;

import net.jescort.domain.forum.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageDao extends JpaRepository<Message, Integer>
{
    public long countBySenderId(int senderId);

    public List<Message> findBySenderId(int senderId);

    public List<Message> findBySenderId(int senderId, Pageable pageable);

    public List<Message> findBySenderId(int senderId, int offset, int limit);

    public long countByRecipientId(int recipientId);

    public List<Message> findByRecipientId(int recipientId);

    public List<Message> findByRecipientId(int recipientId, Pageable pageable);

    public List<Message> findByRecipientId(int recipientId, int offset, int limit);
}

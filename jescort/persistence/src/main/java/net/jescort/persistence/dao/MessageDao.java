package net.jescort.persistence.dao;

import net.jescort.domain.forum.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageDao extends JpaRepository<Message, Integer>
{
    public long countBySenderId(String senderId);

    public List<Message> findBySenderId(String senderId);

    public List<Message> findBySenderId(String senderId, Pageable pageable);

    public List<Message> findBySenderId(String senderId, int offset, int limit);

    public long countByRecipientId(String recipientId);

    public List<Message> findByRecipientId(String recipientId);

    public List<Message> findByRecipientId(String recipientId, Pageable pageable);

    public List<Message> findByRecipientId(String recipientId, int offset, int limit);
}

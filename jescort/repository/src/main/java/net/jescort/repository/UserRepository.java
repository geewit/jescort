package net.jescort.repository;

import net.jescort.domain.forum.Message;
import net.jescort.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Blob;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-14
 * Time: 下午12:00
 */
public interface UserRepository
{
    public User getCurrentUser();

    public void createUser(final User user);

    public void createUser(final String username, final String password, String nickname, final String email, final String timezone, final Locale locale);

    public void updateUser(final User user);
    
    public User getUser(final Integer id);

    public User findUserByUsername(final String username);
    
    public Set<String> findRolesByUsername(final String username);

    public Long countUsers();

    public Blob findPhoto(final Integer id);

    public Message getMessage(final Integer messageId);

    public void sendMessage(final Integer senderId, final String subject, final String content, final Integer... recipientIds);

    public void sendMessage(final Message message);

    public Long countMessageBySender(final Integer senderId);

    public Long countMessageByRecipient(final Integer recipientId);

    public Page<Message> findMessagesBySender(final Integer senderId, final Pageable pageable);

    public Page<Message> findMessagesByRecipient(final Integer recipientId, final Pageable pageable);

    public ModelAndView messageBoxView(final Integer recipientId, final Integer pageNo, final Integer pageSize, final ModelAndView mav);
}

package net.jescort.repository;

import net.jescort.domain.forum.Message;
import net.jescort.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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

    public User createUser(final String username, final String password, String nickname, final String email);

    public void updateUser(final User user);
    
    public User getUser(final String userId);

    public User findUserByUsername(final String username);
    
    public Set<String> findRolesByUsername(final String username);

    public Long countUsers();

    public String uploadAvatar(final MultipartFile multipartFile);

    public String findAvatarPath(final String userId);

    public String convertAvatarPath(String avatar);

    public Message getMessage(final Integer messageId);

    public void sendMessage(final String senderId, final String subject, final String content, final String... recipientIds);

    public void sendMessage(final Message message);

    public Long countMessageBySender(final String senderId);

    public Long countMessageByRecipient(final String recipientId);

    public Page<Message> findMessagesBySender(final String senderId, final Pageable pageable);

    public Page<Message> findMessagesByRecipient(final String recipientId, final Pageable pageable);

    public ModelAndView messageBoxView(final Integer pageNo, final Integer pageSize, final ModelAndView mav);
}

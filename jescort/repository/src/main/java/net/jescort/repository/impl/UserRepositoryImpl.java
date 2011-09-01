package net.jescort.repository.impl;

import javax.annotation.Resource;

import com.google.gson.GsonBuilder;
import net.gelif.kernel.core.data.domain.PageableFactory;
import net.gelif.modules.memcached.MemcachedObjectType;
import net.gelif.modules.memcached.SpyMemcachedClient;
import net.jescort.domain.forum.Message;
import net.jescort.domain.user.Email;
import net.jescort.domain.user.User;
import net.jescort.persistence.dao.MessageDao;
import net.jescort.persistence.dao.UserDao;
import net.jescort.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: admin@gelif.net Date: 11-7-14 Time: 下午12:01
 */
@Service("userRepository")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserRepositoryImpl implements UserRepository
{
    private transient final Log logger = LogFactory.getLog(UserRepositoryImpl.class);

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "messageDao")
    private MessageDao messageDao;

    @Resource(name = "memcachedClient")
    private SpyMemcachedClient memcachedClient;

    @Override
    public User getCurrentUser()
    {
        final Integer currentUserId = (Integer) SecurityUtils.getSubject().getPrincipal();
        if( currentUserId != null )
        {
            return getUser(currentUserId);
        } else
        {
            return null;
        }
    }

    @Transactional
    public void createUser(User user)
    {
        String password = new Sha1Hash(user.getPassword()).toHex();
        user.setPassword(password);
        userDao.save(user);
    }

    @Transactional
    public void createUser(String username, String password, String emailAddress)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(new Sha1Hash(password).toHex());
        Email email = new Email(emailAddress);
        user.getEmails().add(email);
        userDao.save(user);
    }

    @Override
    public User getUser(Integer id)
    {
        if (memcachedClient != null)
        {
            return getUserFromMemcached(id);
        } else
        {
            return userDao.findOne(id);
        }
    }

    private User getUserFromMemcached(Integer id)
    {
        String key = MemcachedObjectType.USER.getPrefix() + id;

        User user;
        String jsonString = memcachedClient.get(key);

        if (jsonString == null)
        {
            user = userDao.findOne(id);
            if (user != null)
            {
                jsonString = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(user);
                memcachedClient.set(key, MemcachedObjectType.USER.getExpiredTime(), jsonString);
            }
        } else
        {
            user = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(jsonString, User.class);
        }
        return user;
    }

    public Long countUsers()
    {
        return userDao.count();
    }

    public User findUserByUsername(String username)
    {
        return userDao.findByUsername(username);
    }

    @Override
    public Blob findPhoto(Integer id)
    {
        return userDao.findPhoto(id);
    }

    @Override
    public Message getMessage(Integer messageId)
    {
        return messageDao.findOne(messageId);
    }

    @Override
    public void sendMessage(Integer senderId, String subject, String content, Integer... recipientIds)
    {
        Message message = new Message();
        message.setSender(new User(senderId));
        List<User> users = new ArrayList<User>(recipientIds.length);
        for(Integer recipientId : recipientIds)
        {
            users.add(new User(recipientId));
        }
        message.setRecipients(users);
        messageDao.save(message);
    }

    @Override
    public void sendMessage(Message message)
    {
        messageDao.save(message);
    }

    @Override
    public Long countMessageBySender(Integer senderId)
    {
        return messageDao.countBySenderId(senderId);
    }

    @Override
    public Long countMessageByRecipient(Integer recipientId)
    {
        return messageDao.countByRecipientId(recipientId);
    }

    @Override
    public Page<Message> findMessagesBySender(Integer senderId, Pageable pageable)
    {
        List<Message> messages = messageDao.findBySenderId(senderId, pageable);
        Page<Message> page = new PageImpl<Message>(messages);
        return page;
    }

    @Override
    public Page<Message> findMessagesByRecipient(Integer recipientId, Pageable pageable)
    {
        List<Message> messages = messageDao.findByRecipientId(recipientId, pageable);
        Page<Message> page = new PageImpl<Message>(messages);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ModelAndView messageBoxView(Integer recipientId, Integer pageNo, Integer pageSize, ModelAndView mav)
    {
        Pageable pageable = PageableFactory.create(pageNo, pageSize);
        List<Message> messages = messageDao.findByRecipientId(recipientId, pageable);
        final long totalPages = messageDao.countByRecipientId(recipientId);
        Page<Message> page = new PageImpl<Message>(messages, pageable, totalPages);
        mav.addObject("messageBox", page);
        return mav;
    }
}

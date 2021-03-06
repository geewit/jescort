/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.repository.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Resource;

import com.google.common.collect.Sets;
import com.google.gson.GsonBuilder;
import net.gelif.kernel.core.data.domain.PageableFactory;
import net.gelif.kernel.core.util.FilepathUtils;
import net.gelif.kernel.core.util.UUIDUtils;
import net.gelif.modules.memcached.MemcachedObjectType;
import net.gelif.modules.memcached.SpyMemcachedClient;
import net.jescort.domain.forum.Message;
import net.jescort.domain.user.Email;
import net.jescort.domain.user.ShiroUser;
import net.jescort.domain.user.User;
import net.jescort.persistence.dao.GroupDao;
import net.jescort.persistence.dao.MessageDao;
import net.jescort.persistence.dao.RoleDao;
import net.jescort.persistence.dao.UserDao;
import net.jescort.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA. User: admin@gelif.net Date: 11-7-14 Time: 下午12:01
 */
@Service("userRepository")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserRepositoryImpl implements UserRepository
{
    private transient final static Log logger = LogFactory.getLog(UserRepositoryImpl.class);
    
    @Resource(name = "userDao")
    private UserDao userDao;
    
    @Resource(name = "roleDao")
    private RoleDao roleDao;
    
    @Resource(name = "groupDao")
    private GroupDao groupDao;
    
    @Resource(name = "messageDao")
    private MessageDao messageDao;
    
    @Resource(name = "memcachedClient")
    private SpyMemcachedClient memcachedClient;
    
    @Value("${settings.avatar.prefix.path}")
    private String avatarPrefixPath;
    
    @Resource(name = "absolutePath", type = String.class)
    private String absolutePath;
    
    @Value("${settings.avatar.default.path}")
    private String defaultAvatarPath;
    
    @Override
    public User getCurrentUser()
    {
        final ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(shiroUser != null)
        {
            return getUser(shiroUser.getId());
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public User getFullCurrentUser()
    {
        final ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        if(shiroUser != null)
        {
            return userDao.findOne(shiroUser.getId());
        }
        else
        {
            return null;
        }
    }
    
    public String getPassword(String userId)
    {
        return userDao.findPasswordByUserId(userId);
    }
    
    @Override
    @Transactional
    public void createUser(User user)
    {
        String id = UUIDUtils.randomUUID();
        user.setId(id);
        String password = new Sha1Hash(user.getPassword()).toHex();
        user.setPassword(password);
        userDao.save(user);
    }
    
    @Override
    @Transactional
    public void updateUser(User user)
    {
        userDao.save(user);
        deleteUserFromMemcached(user.getId());
    }
    
    @Transactional
    public User createUser(String username, String password, String nickname, String emailAddress)
    {
        User user = new User();
        String id = UUIDUtils.randomUUID();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(new Sha1Hash(password).toHex());
        user.setNickname(nickname);
        Email email = new Email(emailAddress);
        email.setPriority(0);
        email.setUser(user);
        user.getEmails().add(email);
        user.setTimezone("+8");
        user.setLocale(Locale.getDefault());
        user.setGroups(Sets.newHashSet(groupDao.findByName("MEMBER")));
        userDao.save(user);
        return user;
    }
    
    @Transactional
    public void changePassword(final String password, final String userId)
    {
        userDao.updatePassword(password, userId);
        deleteUserFromMemcached(userId);
    }
    
    @Override
    public User getUser(String id)
    {
        if(memcachedClient != null)
        {
            return getUserFromMemcached(id);
        }
        else
        {
            return userDao.findOne(id);
        }
    }
    
    private void deleteUserFromMemcached(String userId)
    {
        String key = MemcachedObjectType.USER.getPrefix() + userId;
        memcachedClient.delete(key);
    }
    
    private User getUserFromMemcached(String userId)
    {
        String key = MemcachedObjectType.USER.getPrefix() + userId;
        
        User user;
        String jsonString = memcachedClient.get(key);
        
        if(jsonString == null)
        {
            logger.debug("user not found in memcached");
            user = userDao.findOne(userId);
            if(user != null)
            {
                jsonString = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(user);
                memcachedClient.set(key, MemcachedObjectType.USER.getExpiredTime(), jsonString);
            }
        }
        else
        {
            logger.debug("user found in memcached");
            user = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(jsonString, User.class);
        }
        return user;
    }
    
    @Override
    public User findUserByUsername(String username)
    {
        return userDao.findByUsername(username);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Set<String> findRolesByUsername(String username)
    {
        return new HashSet<String>(roleDao.findByUsername(username));
    }
    
    @Override
    public Long countUsers()
    {
        return userDao.count();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String uploadAvatar(final MultipartFile multipartFile)
    {
        final ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        if(null == shiroUser)
        {
            return null;
        }
        String suffix = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
        String avatarName = shiroUser.getId() + "." + suffix;
        String path = FilepathUtils.filenameTofullFilepath(absolutePath + avatarPrefixPath, avatarName);
        
        final File avatarPath = new File(path);
        avatarPath.mkdirs();
        try
        {
            multipartFile.transferTo(avatarPath);
            //FileCopyUtils.copy(multipartFile.getBytes(), avatarPath);
        }
        catch(IOException e)
        {
            logger.warn(e.toString());
        }
        userDao.updateAvatar(avatarName, shiroUser.getId());
        deleteUserFromMemcached(shiroUser.getId());
        return avatarName;
    }
    
    @Override
    public String findAvatarPath(String userId)
    {
        String avatar = userDao.findAvatar(userId);
        if(StringUtils.isNotBlank(avatar))
        {
            return convertAvatarPath(avatar);
        }
        else
        {
            return defaultAvatarPath;
        }
    }
    
    private String convertAvatarPath(String avatar)
    {
        return FilepathUtils.filenameTofullFilepath(absolutePath + avatarPrefixPath, avatar);
    }
    
    @Override
    public Message getMessage(Integer messageId)
    {
        return messageDao.findOne(messageId);
    }
    
    @Override
    public void sendMessage(final String senderId, final String subject, final String content, final String... recipientIds)
    {
        Message message = new Message();
        message.setSender(new User(senderId));
        List<User> users = new ArrayList<User>(recipientIds.length);
        for(String recipientId : recipientIds)
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
    public Long countMessageBySender(String senderId)
    {
        return messageDao.countBySenderId(senderId);
    }
    
    @Override
    public Long countMessageByRecipient(String recipientId)
    {
        return messageDao.countByRecipientId(recipientId);
    }
    
    @Override
    public Page<Message> findMessagesBySender(String senderId, Pageable pageable)
    {
        List<Message> messages = messageDao.findBySenderId(senderId, pageable);
        Page<Message> page = new PageImpl<Message>(messages);
        return page;
    }
    
    @Override
    public Page<Message> findMessagesByRecipient(String recipientId, Pageable pageable)
    {
        List<Message> messages = messageDao.findByRecipientId(recipientId, pageable);
        Page<Message> page = new PageImpl<Message>(messages);
        return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ModelAndView messageBoxView(Integer pageNo, Integer pageSize, ModelAndView mav)
    {
        final ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        if(null != shiroUser)
        {
            String recipientId = shiroUser.getId();
            Pageable pageable = PageableFactory.create(pageNo, pageSize);
            List<Message> messages = messageDao.findByRecipientId(recipientId, pageable);
            final long totalPages = messageDao.countByRecipientId(recipientId);
            Page<Message> page = new PageImpl<Message>(messages, pageable, totalPages);
            mav.addObject("messageBox", page);
        }
        return mav;
    }
}

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
    
    public User getFullCurrentUser();

    public String getPassword(String userId);

    public void createUser(final User user);

    public User createUser(final String username, final String password, String nickname, final String email);

    public void changePassword(final String password, final String userId);

    public void updateUser(final User user);
    
    public User getUser(final String userId);

    public User findUserByUsername(final String username);
    
    public Set<String> findRolesByUsername(final String username);

    public Long countUsers();

    public String uploadAvatar(final MultipartFile multipartFile);

    public String findAvatarPath(final String userId);

    public Message getMessage(final Integer messageId);

    public void sendMessage(final String senderId, final String subject, final String content, final String... recipientIds);

    public void sendMessage(final Message message);

    public Long countMessageBySender(final String senderId);

    public Long countMessageByRecipient(final String recipientId);

    public Page<Message> findMessagesBySender(final String senderId, final Pageable pageable);

    public Page<Message> findMessagesByRecipient(final String recipientId, final Pageable pageable);

    public ModelAndView messageBoxView(final Integer pageNo, final Integer pageSize, final ModelAndView mav);
}

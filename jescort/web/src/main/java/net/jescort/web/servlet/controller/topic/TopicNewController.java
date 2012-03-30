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
package net.jescort.web.servlet.controller.topic;

import net.jescort.domain.forum.Forum;
import net.jescort.domain.forum.Post;
import net.jescort.domain.forum.Topic;
import net.jescort.domain.user.User;
import net.jescort.repository.EscortRepository;
import net.jescort.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes(types = {Topic.class})
@RequestMapping(value = {"/forums/{forumId}/new"})
public class TopicNewController
{
    private transient final static Log logger = LogFactory.getLog(TopicNewController.class);

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("forumId") Integer forumId, Model model)
    {
        if (!escortRepository.forumExists(forumId))
        {
            //TODO return some error or throw exception
            return "error";
        }
        Forum forum = escortRepository.getForum(forumId);
        TopicCommand command = new TopicCommand();
        command.setForumId(forumId);
        model.addAttribute(command);
        model.addAttribute("forum", forum);
        return "topics/new";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute("command") @Valid TopicCommand command, BindingResult result, HttpServletRequest request, SessionStatus status)
    {
        if (result.hasErrors())
        {
            logger.debug("result.hasErrors");
            return "topics/new";
        } else
        {
            final User currentUser = userRepository.getCurrentUser();
            Post rootPost = new Post();
            rootPost.setPoster(currentUser);
            rootPost.setContent(command.getContent());
            Topic topic = new Topic();
            topic.setSubject(command.getSubject());
            topic.setForumId(command.getForumId());
            rootPost.setTopic(topic);
            topic.setRootPost(rootPost);
            escortRepository.createTopic(topic, request);
            status.setComplete();
            return "redirect:/topics/" + topic.getId();
        }
    }
}

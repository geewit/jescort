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
package net.jescort.web.servlet.controller.post;

import net.jescort.domain.forum.Post;
import net.jescort.domain.user.User;
import net.jescort.repository.EscortRepository;
import net.jescort.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@SessionAttributes(types = Post.class)
@RequestMapping(value = {"/posts/{id}/quote"})
public class PostQuoteController
{
    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("id") Integer id, Model model)
    {
        Post quotePost = escortRepository.quotePost(id);
        model.addAttribute(quotePost);
        return "posts/new";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute("post") @Valid Post post, BindingResult result, SessionStatus status)
    {
        if (result.hasErrors())
        {
            return "posts/new";
        } else
        {
            User currentUser = userRepository.getCurrentUser();
            escortRepository.savePost(post, currentUser);
            status.setComplete();
            return "redirect:/topics/" + post.getTopic().getId();
        }
    }
}

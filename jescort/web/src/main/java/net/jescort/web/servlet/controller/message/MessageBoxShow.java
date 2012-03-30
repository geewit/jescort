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
package net.jescort.web.servlet.controller.message;

import net.jescort.domain.forum.Message;
import net.jescort.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-16
 * Time: 下午7:02
 */
@Controller
@SessionAttributes(types = Message.class)
public class MessageBoxShow
{
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(value = {"/messages"}, method = RequestMethod.GET)
    public ModelAndView messageBoxHandler()
    {
        return messageBoxModelAndView(null, null);
    }

    @RequestMapping(value = {"/messages/page/{page}"}, method = RequestMethod.GET)
    public ModelAndView messageBoxHandler(@PathVariable("page") Integer page)
    {
        return messageBoxModelAndView(page, null);
    }

    @RequestMapping(value = {"/messages/page/{page}/pageSize/{pageSize}"}, method = RequestMethod.GET)
    public ModelAndView messageBoxHandler(@PathVariable("page") Integer page, @Param("pageSize") Integer pageSize)
    {
        return messageBoxModelAndView(page, pageSize);
    }

    private ModelAndView messageBoxModelAndView(Integer pageNo, Integer pageSize)
    {
        ModelAndView mav = new ModelAndView("messages/messageBox");
        return userRepository.messageBoxView(pageNo, pageSize, mav);
    }
}

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
package net.jescort.web.servlet.controller.forum;

import javax.annotation.Resource;

import net.jescort.domain.forum.Forum;
import net.jescort.domain.forum.Topic;
import net.jescort.repository.EscortRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes(types = Forum.class)
public class ForumShow
{
    //private transient final static Log logger = LogFactory.getLog(ForumShow.class);

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;

    @RequestMapping(value = {"/forums"}, method = RequestMethod.GET)
    public ModelAndView forumHandler()
    {
        return new ModelAndView(new RedirectView("/categories"));
    }

    @RequestMapping(value = {"/forums/{id}"}, method = RequestMethod.GET)
    public ModelAndView forumHandler(@PathVariable("id") Integer id)
    {
        return forumModelAndView(id, null, null);
    }

    @RequestMapping(value = {"/forums/{id}/page/{pageNo}"}, method = RequestMethod.GET)
    public ModelAndView forumHandler(@PathVariable("id") Integer id, @PathVariable("pageNo") Integer pageNo)
    {
        return forumModelAndView(id, pageNo, 10);
    }

    @RequestMapping(value = {"/forums/{id}/page/{pageNo}/pagesize/{pageSize}"}, method = RequestMethod.GET)
    public ModelAndView forumHandler(@PathVariable("id") Integer id, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize)
    {
        return forumModelAndView(id, pageNo, pageSize);
    }

    private ModelAndView forumModelAndView(Integer id, Integer pageNo, Integer pageSize)
    {
        ModelAndView mav = new ModelAndView("forums/show");
        Forum forum = escortRepository.getForum(id);
        Pageable pageable = new PageRequest(null != pageNo && pageNo > 0 ? pageNo : 0, null != pageSize && pageSize > 0 ? pageSize : 10);
        Page<Topic> topics = escortRepository.findTopicsByForumWithBBCodeToHtml(id, pageable);
        mav.addObject("forum", forum);
        mav.addObject("topics", topics);
        return mav;
    }
}

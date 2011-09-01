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

@Controller
@SessionAttributes(types = Forum.class)
public class ForumShow
{
    @RequestMapping(value = {"/forums/{id}"}, method = RequestMethod.GET)
    public ModelAndView forumHandler(@PathVariable("id") Integer id)
    {
        return forumModelAndView(id, null, null);
    }
    
    @RequestMapping(value = {"/forums/{id}/page/{pageNo}/pageSize/{pageSize}"}, method = RequestMethod.GET)
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
    
    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

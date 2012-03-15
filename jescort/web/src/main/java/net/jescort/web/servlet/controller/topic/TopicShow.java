package net.jescort.web.servlet.controller.topic;

import net.jescort.domain.forum.Topic;
import net.jescort.repository.EscortRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
@SessionAttributes(types = Topic.class)
public class TopicShow
{
    @RequestMapping(value = {"/topics"}, method = RequestMethod.GET)
    public ModelAndView topicHandler()
    {
        return new ModelAndView(new RedirectView("/categories"));
    }

    @RequestMapping(value = {"/topics/{id}"}, method = RequestMethod.GET)
    public ModelAndView topicHandler(@PathVariable("id") Integer id)
    {
        return topicModelAndView(id, null, null);
    }

    @RequestMapping(value = {"/topics/{id}/page/{pageNo}"}, method = RequestMethod.GET)
    public ModelAndView topicHandler(@PathVariable("id") Integer id, @PathVariable("pageNo") Integer pageNo)
    {
        return topicModelAndView(id, pageNo, 10);
    }


    @RequestMapping(value = {"/topics/{id}/page/{pageNo}/pagesize/{pageSize}"}, method = RequestMethod.GET)
    public ModelAndView topicHandler(@PathVariable("id") Integer id, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize)
    {
        return topicModelAndView(id, pageNo, pageSize);
    }

    private ModelAndView topicModelAndView(Integer id, Integer pageNo, Integer pageSize)
    {
        ModelAndView mav = new ModelAndView("topics/show");
        return escortRepository.topicView(id, pageNo, pageSize, mav);
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

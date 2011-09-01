package net.jescort.web.servlet.controller.topic;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.jescort.domain.forum.Post;
import net.jescort.domain.user.User;
import net.jescort.repository.EscortRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes(types = Post.class)
@RequestMapping(value = {"/topics/{topicId}/reply", "/posts/new"})
public class TopicReplyController
{
    @RequestMapping(value = {"/topics/{topicId}/reply"}, method = RequestMethod.GET)
    public String setupForm(@PathVariable("topicId") Integer topicId, Model model)
    {
        Post post = new Post();
        post.setTopicId(topicId);
        model.addAttribute(post);
        return "posts/new";
    }
    
    @RequestMapping(value = {"/topics/{topicId}/reply", "/posts/new"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute("post") @Valid Post post, BindingResult result, HttpServletRequest request, SessionStatus status)
    {
        if(result.hasErrors())
        {
            return "posts/new";
        }
        else
        {
            User currentUser = (User)SecurityUtils.getSubject().getPrincipal();
            post.setPoster(currentUser);
            escortRepository.replyTopic(post, request);
            status.setComplete();
            return "redirect:/posts/" + post.getId();
        }
    }
    
    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

package net.jescort.web.servlet.controller.topic;

import net.jescort.domain.forum.Forum;
import net.jescort.domain.forum.Post;
import net.jescort.domain.forum.Topic;
import net.jescort.domain.user.User;
import net.jescort.repository.EscortRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
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
            final User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
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

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

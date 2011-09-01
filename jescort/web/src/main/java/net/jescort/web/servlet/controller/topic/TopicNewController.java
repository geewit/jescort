package net.jescort.web.servlet.controller.topic;

import net.jescort.domain.forum.Post;
import net.jescort.domain.forum.Topic;
import net.jescort.repository.EscortRepository;
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
@RequestMapping(value = {"/forums/{forumId}/topics/new"})
public class TopicNewController
{
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("forumId") Integer forumId, Model model)
    {
        TopicCommand command = new TopicCommand();
        if (!escortRepository.forumExists(forumId))
        {
            //TODO return some error or throw exception
            return "error";
        }
        command.setForumId(forumId);
        model.addAttribute(command);
        return "topics/new";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute @Valid TopicCommand command, BindingResult result, HttpServletRequest request, SessionStatus status)
    {
        if (result.hasErrors())
        {
            return "topics/new";
        }
        else
        {
            Topic topic = new Topic();
            topic.setSubject(command.getSubject());
            Post rootPost = new Post();
            rootPost.setContent(command.getContent());
            topic.setRootPost(rootPost);
            escortRepository.createTopic(topic, request);
            status.setComplete();
            return "redirect:/topics/" + topic.getId();
        }
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

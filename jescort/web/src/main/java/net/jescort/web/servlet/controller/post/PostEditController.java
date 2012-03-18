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
@SessionAttributes("post")
@RequestMapping("/posts/{id}/edit")
public class PostEditController
{
    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("id") Integer id, Model model)
    {
        Post post = escortRepository.getPost(id);
        if (null == post)
        {
            //TODO return some error or throw exception
            return "error";
        }
        /*
        if (!post.getPoster().equals(SecurityUtils.getCurrentUser()))
        {
            //TODO return some error or throw exception
            return "error";
        }
        */
        model.addAttribute("post", post);
        return "posts/edit";
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute("post") @Valid Post post, BindingResult result, SessionStatus status)
    {
        if (result.hasErrors())
        {
            return "posts/edit";
        } else
        {
            User currentUser = userRepository.getCurrentUser();
            escortRepository.savePost(post, currentUser);
            status.setComplete();
            return "redirect:/posts/" + post.getId();
        }
    }
}

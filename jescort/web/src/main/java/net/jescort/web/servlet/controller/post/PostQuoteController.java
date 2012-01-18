package net.jescort.web.servlet.controller.post;

import net.jescort.domain.forum.Post;
import net.jescort.domain.user.User;
import net.jescort.repository.EscortRepository;
import org.apache.shiro.SecurityUtils;
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
            User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
            escortRepository.updatePost(post, currentUser);
            status.setComplete();
            return "redirect:/topics/" + post.getTopicId();
        }
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

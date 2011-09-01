package net.jescort.web.servlet.controller.post;

import net.jescort.domain.forum.Post;
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
@SessionAttributes(types = Post.class)
public class PostShow
{
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView postHandler(@PathVariable("id") Integer id)
    {
        Post post = escortRepository.getPost(id);
        Integer topicId = post.getTopicId();
        long totalTopics = escortRepository.rownumberPostsByTopicIdAndId(topicId, id);
        long totalPages = totalTopics / 10 + 1;
        return new ModelAndView(new RedirectView("/topics/" + topicId + "/page/" + totalPages + "#" + id, true));
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

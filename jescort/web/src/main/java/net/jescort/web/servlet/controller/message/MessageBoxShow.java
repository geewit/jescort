package net.jescort.web.servlet.controller.message;

import net.jescort.domain.forum.Message;
import net.jescort.domain.user.User;
import net.jescort.persistence.dao.MessageDao;
import net.jescort.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
        final Integer currentUserId = (Integer) SecurityUtils.getSubject().getPrincipal();
        ModelAndView mav = new ModelAndView("messages/messageBox");
        return userRepository.messageBoxView(currentUserId, pageNo, pageSize, mav);
    }
}

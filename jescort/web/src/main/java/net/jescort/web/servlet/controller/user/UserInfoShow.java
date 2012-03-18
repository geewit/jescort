package net.jescort.web.servlet.controller.user;


import net.jescort.domain.user.User;
import net.jescort.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-9
 * Time: 下午10:13
 */
@Controller
public class UserInfoShow
{
    private transient final Log logger = LogFactory.getLog(UserInfoShow.class);

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(value = {"/users/{id}"}, method = RequestMethod.GET)
    public ModelAndView userHandler(@PathVariable("id") String id)
    {
        ModelAndView mav = new ModelAndView("users/profile");
        User user = userRepository.getUser(id);
        mav.addObject("user", user);
        return mav;
    }
}

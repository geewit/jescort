package net.jescort.web.servlet.controller.auth;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-14
 * Time: 下午12:37
 */
@Controller
@RequestMapping(value = {"/auth/logout"})
public class LogoutController
{
    @RequestMapping(method = RequestMethod.GET)
    public String logout()
    {
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }
}

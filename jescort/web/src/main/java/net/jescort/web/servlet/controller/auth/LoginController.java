package net.jescort.web.servlet.controller.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-15
 * Time: 下午2:21
 */
@Controller
@RequestMapping(value = {"/login"})
public class LoginController
{
    private final static Log logger = LogFactory.getLog(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginForm(@ModelAttribute("command") LoginCommand command)
    {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitLoginForm(@ModelAttribute("command") @Valid LoginCommand command, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(command.getUsername(), command.getPassword(), command.getRememberMe());
        try
        {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e)
        {
            result.reject("error.invalidLogin", "The username or password was not correct.");
            return "login";
        }
        return "redirect:/";
    }
}

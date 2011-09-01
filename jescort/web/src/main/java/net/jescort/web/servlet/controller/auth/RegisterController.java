package net.jescort.web.servlet.controller.auth;

import net.gelif.kernel.core.util.TimeZoneUtils;
import net.jescort.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-14
 * Time: 上午11:44
 */
@Controller
@RequestMapping(value = {"/auth/register"})
public class RegisterController
{
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showSignupForm(Model model, @ModelAttribute("command") RegisterCommand command)
    {
        model.addAttribute("timeZones", TimeZoneUtils.ALL_TIMEZONES);
        return "auth/register";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String submitSignupForm(Model model, @ModelAttribute("command") @Valid RegisterCommand command, BindingResult result)
    {
        if (result.hasErrors())
        {
            return showSignupForm(model, command);
        }

        // Create the auth
        userRepository.createUser(command.getUsername(), command.getPassword(), command.getEmail());

        // Login the newly created auth
        SecurityUtils.getSubject().login(new UsernamePasswordToken(command.getUsername(), command.getPassword()));

        return "redirect:/";
    }
}

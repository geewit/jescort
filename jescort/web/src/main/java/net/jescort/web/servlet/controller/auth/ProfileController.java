package net.jescort.web.servlet.controller.auth;

import net.gelif.kernel.core.util.BeanUtils;
import net.gelif.kernel.core.util.TimeZoneUtils;
import net.jescort.domain.user.User;
import net.jescort.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-1-29
 * Time: 下午2:07
 */
@Controller
@SessionAttributes("profile")
@RequestMapping("/auth/profile")
public class ProfileController
{
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        User currentUser = userRepository.getCurrentUser();
        if (null == currentUser)
        {
            //TODO return some error or throw exception
            return "error";
        }
        //command.setLocale(currentUser.getLocale().toString());

        model.addAttribute("user", currentUser);
        model.addAttribute("timeZones", TimeZoneUtils.ALL_TIMEZONES);
        return "auth/profile";
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute("user") @Valid User user, BindingResult result, SessionStatus status)
    {
        if (result.hasErrors())
        {
            return "auth/profile";
        } else
        {

            User currentUser = userRepository.getCurrentUser();
            BeanUtils.copyProperties(user, currentUser);
            userRepository.updateUser(currentUser);
            status.setComplete();
            return "redirect:/auth/profile";
        }
    }
}

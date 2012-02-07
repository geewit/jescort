package net.jescort.web.servlet.controller.auth;

import net.jescort.domain.enums.Gender;
import net.jescort.domain.forum.Post;
import net.jescort.domain.user.Name;
import net.jescort.domain.user.Profile;
import net.jescort.domain.user.User;
import net.jescort.repository.EscortRepository;
import net.jescort.repository.UserRepository;
import org.apache.commons.lang.LocaleUtils;
import org.apache.shiro.SecurityUtils;
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
public class ProfileEditController
{
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        User currentUser = userRepository.getCurrentUser() ;
        if (null == currentUser)
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
        ProfileCommand command = new ProfileCommand();
        command.setUsername(currentUser.getUsername());
        command.setNickname(currentUser.getNickname());
        command.setTimezone(currentUser.getTimezone());
        //command.setLocale(currentUser.getLocale().toString());
        Profile profile = currentUser.getProfile();
        if(null != profile)
        {
            Name name = profile.getName();
            if(null != name)
            {
                command.setFamaliyName(name.getFamaliyName());
                command.setGivenName(name.getGivenName());
            }
            command.setGender(profile.getGender().name());
            command.setBirthday(profile.getBirthday());
        }

        model.addAttribute("command", command);
        return "auth/profile";
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute("command") @Valid ProfileCommand command, BindingResult result, SessionStatus status)
    {
        if (result.hasErrors())
        {
            return "auth/profile";
        } else
        {
            User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
            currentUser.setNickname(command.getNickname());
            currentUser.getProfile().setBirthday(command.getBirthday());
            currentUser.getProfile().setGender(Gender.valueOf(command.getGender().toUpperCase()));
            currentUser.getProfile().setName(new Name(command.getFamaliyName(), command.getGivenName()));
            userRepository.updateUser(currentUser);
            status.setComplete();
            return "redirect:/auth/profile";
        }
    }

    @Resource(name = "userRepository")
    private UserRepository userRepository;
}

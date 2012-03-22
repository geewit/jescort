package net.jescort.web.servlet.controller.auth;

import net.gelif.kernel.core.util.BeanUtils;
import net.gelif.kernel.core.util.TimeZoneUtils;
import net.jescort.domain.user.User;
import net.jescort.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

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
    private transient final static Log logger = LogFactory.getLog(ProfileController.class);
    
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
    public String processSubmit(@ModelAttribute("user") User user, Model model, BindingResult result, SessionStatus status)
    {
        if (result.hasErrors())
        {
            return "auth/profile";
        } else
        {
            /*
            if(null != user.getProperties() && !user.getProperties().isEmpty())
            {
                if(logger.isDebugEnabled())
                {
                    for(String key : user.getProperties().keySet())
                    {
                        logger.debug("user.properties['" + key + "'] == " + user.getProperties().get(key));
                    }
                }
                Set<String> keys = new HashSet<String>();
                for(String key : user.getProperties().keySet())
                {
                    if(StringUtils.isBlank(user.getProperties().get(key)))
                    {
                        keys.add(key);
                    }
                }
                for(String key : keys)
                {
                    logger.debug("delete key = " + key + " from user.properties");
                    user.getProperties().remove(key);
                }
            }
            */
            User currentUser = userRepository.getFullCurrentUser();
            BeanUtils.copyProperties(user, currentUser);
            userRepository.updateUser(currentUser);
            model.addAttribute("user", currentUser);
            model.addAttribute("timeZones", TimeZoneUtils.ALL_TIMEZONES);
            status.setComplete();
            return "redirect:/auth/profile";
        }
    }
}

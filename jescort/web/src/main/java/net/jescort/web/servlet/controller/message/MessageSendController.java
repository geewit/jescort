package net.jescort.web.servlet.controller.message;

import net.jescort.domain.forum.Message;
import net.jescort.domain.user.User;
import net.jescort.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-11
 * Time: 上午11:55
 */
@Controller
@SessionAttributes(types = Message.class)
@RequestMapping(value = {"/messages/new"})
public class MessageSendController
{
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String showSendMessageForm(@ModelAttribute Message message, Model model, HttpServletRequest request)
    {
        model.addAttribute("message", new Message());
        model.addAttribute ("title", messageSource.getMessage("message.send_message.title", new String[]{}, request.getLocale()));
        return "auth/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendMessageForm(@RequestParam("recipientIds") Integer[] recipientIds, @ModelAttribute("message") @Valid Message message, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "auth/register";
        }
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        message.setSender(currentUser);
        List<User> recipients = new ArrayList<User>(recipientIds.length);
        for(Integer recipientId : recipientIds)
        {
            recipients.add(new User(recipientId));
        }
        message.setRecipients(recipients);
        userRepository.sendMessage(message);
        return "redirect:/messages";
    }
}

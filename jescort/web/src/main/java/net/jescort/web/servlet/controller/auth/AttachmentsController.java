package net.jescort.web.servlet.controller.auth;

import net.jescort.domain.user.ShiroUser;
import net.jescort.repository.EscortRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-2-8
 * Time: 下午12:16
 */
@Controller
public class AttachmentsController
{
    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;

    @RequestMapping(value = {"/auth/attachments/page/{pageNo}"}, method = RequestMethod.GET)
    public ModelAndView attachmentsHandler(@PathVariable("pageNo") Integer pageNo)
    {
        final ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        ModelAndView mav = new ModelAndView("auth/attachments");
        return escortRepository.attachmentView(shiroUser.getId(), pageNo, 10, mav);
    }
}

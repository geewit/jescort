package net.jescort.web.servlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 下午5:03
 */
@Controller
@RequestMapping("/rules")
public class RulesController
{
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcomeHandler()
    {
        return new ModelAndView("rules");
    }
}

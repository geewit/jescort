package net.jescort.web.servlet.controller;

import net.jescort.domain.forum.Category;
import net.jescort.repository.EscortRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
public class WelcomeController
{
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcomeHandler()
    {
        ModelAndView mav = new ModelAndView("index");
        List<Category> categories = escortRepository.findCategories();
        mav.addObject("categories", categories);
        return mav;
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

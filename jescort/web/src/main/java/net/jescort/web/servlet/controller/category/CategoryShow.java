package net.jescort.web.servlet.controller.category;

import net.jescort.domain.forum.Category;
import net.jescort.repository.EscortRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@SessionAttributes(types = Category.class)
public class CategoryShow
{
    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ModelAndView postHandler(@PathVariable("id") Integer id)
    {
        ModelAndView mav = new ModelAndView("categories/show");
        Category category = escortRepository.findCategory(id);
        mav.addObject(category);
        return mav;
    }
}

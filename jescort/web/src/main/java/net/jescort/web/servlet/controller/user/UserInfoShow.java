package net.jescort.web.servlet.controller.user;


import net.jescort.domain.user.User;
import net.jescort.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-9
 * Time: 下午10:13
 */
@Controller
public class UserInfoShow
{
    private transient final Log logger = LogFactory.getLog(UserInfoShow.class);

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(value = {"/users/{id}"}, method = RequestMethod.GET)
    public ModelAndView userHandler(@PathVariable("id") Integer id)
    {
        ModelAndView mav = new ModelAndView("users/profile");
        User user = userRepository.getUser(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/users/{id}/avatar", method = RequestMethod.GET)
    public void download(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        try
        {
            Blob avatarBlob = userRepository.findAvatar(id);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(MediaType.IMAGE_PNG.toString());
            OutputStream out = response.getOutputStream();
            IOUtils.copy(avatarBlob.getBinaryStream(), out);
            out.flush();
            out.close();

        } catch (IOException e)
        {
            logger.warn(e.getMessage());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

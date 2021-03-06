/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.web.servlet.controller.auth;


import javax.annotation.Resource;

import net.jescort.domain.user.ShiroUser;
import net.jescort.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-16
 * Time: 上午6:40
 */

@Controller
@RequestMapping(value = "/auth/avatar")
public class AvatarController
{
    private transient final Log logger = LogFactory.getLog(AvatarController.class);

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        final ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(null != shiroUser)
        {
            String avatar = shiroUser.getAvatar();
            model.addAttribute("avatarPath", avatar);
        }

        return "auth/avatar";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ModelAndView handleFormUpload(@RequestParam("avatar") MultipartFile multipartFile)
    {
        ModelAndView mav = new ModelAndView("auth/avatar");
        String avatarPath = userRepository.uploadAvatar(multipartFile);
        mav.addObject("avatarPath", avatarPath);
        return mav;
    }
}

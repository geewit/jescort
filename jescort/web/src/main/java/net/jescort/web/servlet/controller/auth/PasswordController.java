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


import net.jescort.domain.user.ShiroUser;
import net.jescort.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-19
 * Time: 下午6:02
 */
@Controller
@RequestMapping(value = "/auth/password")
public class PasswordController
{
    private transient final static Log logger = LogFactory.getLog(PasswordController.class);

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model, @ModelAttribute("command") PasswordCommand command)
    {
        return "auth/password";
    }


    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String processSubmit(@ModelAttribute("command") @Valid PasswordCommand command, BindingResult result, SessionStatus status)
    {
        if (result.hasErrors())
        {
            return "auth/password";
        } else
        {

            final ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
            final String oldPassword = userRepository.getPassword(shiroUser.getId());

            if(!new Sha1Hash(command.getOldPassword()).toHex().equals(oldPassword))
            {
                result.reject("message.password_not_correct", "The password was not correct.");
                return "auth/password";
            }
            userRepository.changePassword(new Sha1Hash(command.getPassword()).toHex(), shiroUser.getId());
            status.setComplete();
            return "auth/password";
        }
    }
}

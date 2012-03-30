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

import net.gelif.kernel.core.util.LocaleUtils;
import net.gelif.kernel.core.util.TimeZoneUtils;
import net.jescort.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-14
 * Time: 上午11:44
 */
@Controller
@RequestMapping(value = {"/register"})
public class RegisterController
{
    private transient final static Log logger = LogFactory.getLog(RegisterController.class);

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showSignupForm(Model model, @ModelAttribute("command") RegisterCommand command, HttpServletRequest request)
    {
        model.addAttribute("timeZones", TimeZoneUtils.ALL_TIMEZONES);
        model.addAttribute("locales", LocaleUtils.ALL_AVALIABLE_LOCALES);
        model.addAttribute("currentLocale", request.getLocale().toString());
        return "register";
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String submitSignupForm(Model model, @ModelAttribute("command") @Valid RegisterCommand command, BindingResult result, HttpServletRequest request)
    {
        if (result.hasErrors())
        {
            logger.debug("result.hasErrors == " + result.getFieldError().getField());
            return showSignupForm(model, command, request);
        }
        // Create the auth
        userRepository.createUser(command.getUsername(), command.getPassword(), command.getNickname(), command.getEmail());

        // Login the newly created auth
        SecurityUtils.getSubject().login(new UsernamePasswordToken(command.getUsername(), command.getPassword()));

        return "redirect:/";
    }
}

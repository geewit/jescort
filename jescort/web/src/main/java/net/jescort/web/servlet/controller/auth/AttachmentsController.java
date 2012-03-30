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

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
package net.jescort.web.servlet.controller.attachment;

import net.jescort.domain.forum.Attachment;
import net.jescort.repository.EscortRepository;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-31
 * Time: 上午9:59
 */
@Controller
public class AttachmentDownloadController
{
    private transient final Log logger = LogFactory.getLog(AttachmentDownloadController.class);

    @RequestMapping(value = "/attachments/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") Integer id, HttpServletResponse response)
    {
        try
        {
            Attachment attachment = escortRepository.findAttachment(id);
            response.setHeader("Content-Disposition", "inline;filename=\"" + attachment.getOriginalName() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(attachment.getContentType());
            IOUtils.copy(new ByteArrayInputStream(attachment.getContent()), out);
            out.flush();
            out.close();

        } catch (IOException e)
        {
            logger.warn(e.getMessage());
        }
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}

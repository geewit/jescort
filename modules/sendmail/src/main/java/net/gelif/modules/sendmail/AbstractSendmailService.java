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
package net.gelif.modules.sendmail;

import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import net.gelif.kernel.core.AbstractLogger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

public abstract class AbstractSendmailService extends AbstractLogger
{
    public abstract void send(SimpleMailMessage mailMessage) throws MailException;

    public abstract void send(SimpleMailMessage mailMessage, String templateName, Map<String, Object> model) throws MailException;

    public String generateEmailContent(String templateName, Map<String, Object> model)
    {
        String content = null;

        try
        {
            content = VelocityEngineUtils.mergeTemplateIntoString(mailTemplateEngine, templateName, model);
        } catch (VelocityException e)
        {
            logger.error(e.getMessage(), e);
        }
        return content;
    }

    public void sendMessage(String[] emails, ClassPathResource resource, String body, String subject, String attachment) throws MessagingException
    {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(emails);
        helper.setText(body);
        helper.setSubject(subject);
        helper.addAttachment(attachment, resource);
        mailSender.send(message);
    }

    /*
    @Required
    public void setMailTemplateEngine(FreeMarkerConfigurer freemarkerConfigurer)
    {
        mailTemplateEngine = freemarkerConfigurer;
    }
    private FreeMarkerConfigurer mailTemplateEngine;
    */

    public void setMailTemplateEngine(VelocityEngine velocityEngine)
    {
        mailTemplateEngine = velocityEngine;
    }

    private VelocityEngine mailTemplateEngine;

    public void setMailSender(final JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    protected JavaMailSender mailSender;
}

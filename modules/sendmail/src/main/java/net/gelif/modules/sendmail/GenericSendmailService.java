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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

public class GenericSendmailService extends AbstractSendmailService
{
    @Override
    public void send(final SimpleMailMessage mailMessage) throws MailException
    {
        try
        {
            mailSender.send(mailMessage);
        } catch (final MailException e)
        {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void send(final SimpleMailMessage mailMessage, final String templateName, final Map<String, Object> model) throws MailException
    {
        final String content = generateEmailContent(templateName, model);
        MimeMessage mimeMessage = null;
        try
        {
            mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(mailMessage.getTo());
            if (!ArrayUtils.isEmpty(mailMessage.getBcc()))
            {
                helper.setBcc(mailMessage.getBcc());
            }
            helper.setSubject(mailMessage.getSubject());
            if (StringUtils.isBlank(mailMessage.getFrom()))
            {
                helper.setFrom(defaultFrom);
            } else
            {
                helper.setFrom(mailMessage.getFrom());
            }
            helper.setText(content, true);
        } catch (final MessagingException ex)
        {
            logger.error(ex.getMessage());
        }
        mailMessage.setText(content);

        mailSender.send(mimeMessage);
    }

    public void setFrom(String from)
    {
        defaultFrom = from;
    }

    private String defaultFrom;
}

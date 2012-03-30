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
package net.gelif.test.modules.sendmail;

import java.util.Map;

import net.gelif.modules.sendmail.AbstractSendmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

/**
 * Mock Mail Service, print mail on screen
 * @author admin@gelif.net
 */
public class MockSendmailService extends AbstractSendmailService
{
    @Override
    public void send(SimpleMailMessage mailMessage) throws MailException
    {
        StringBuffer mail = new StringBuffer();
        mail.append(this.getMailHeader(mailMessage) + "\n");
        mail.append(mailMessage.getText());
        logger.info(mail.toString());
    }

    @Override
    public void send(SimpleMailMessage mailMessage, String templateName, Map<String, Object> model) throws MailException
    {
        StringBuffer mail = new StringBuffer();
        mail.append(this.getMailHeader(mailMessage) + "\n");
        mail.append(this.generateEmailContent(templateName, model));
        logger.info(mail.toString());
    }

    protected String getMailHeader(SimpleMailMessage mailMessage)
    {
        StringBuffer header = new StringBuffer();

        header.append("To: ");
        for(String to : mailMessage.getTo())
        {
            header.append(to).append(";");
        }
        header.append("\nFrom: " + mailMessage.getFrom());
        header.append("\nSubject: " + mailMessage.getSubject());
        return header.toString();
    }
}

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

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.Assert;

/**
 * 邮件消息创建的工具类
 *
 * @author admin@gelif.net
 */
public class SimpleMailMessageUtils
{

    /**
     * 创建html类型的邮件
     */
    public static HtmlMimeMessagePreparator toHtmlMsg(final SimpleMailMessage msg)
    {
        return toHtmlMsg(msg, null);
    }

    /**
     * 创建html类型的邮件
     *
     * @param msg          邮件消息
     * @param fromPersonal 发件人的名称
     * @return
     */
    public static HtmlMimeMessagePreparator toHtmlMsg(final SimpleMailMessage msg, final String fromPersonal)
    {
        return new HtmlMimeMessagePreparator(msg, fromPersonal);
    }

    public static class HtmlMimeMessagePreparator implements MimeMessagePreparator
    {
        private SimpleMailMessage simpleMailMessage;
        private String fromPersonal;

        public HtmlMimeMessagePreparator(SimpleMailMessage simpleMailMessage)
        {
            this(simpleMailMessage, null);
        }

        public HtmlMimeMessagePreparator(SimpleMailMessage simpleMailMessage, String fromPersonal)
        {
            super();
            setSimpleMailMessage(simpleMailMessage);
            this.fromPersonal = fromPersonal;
        }

        public SimpleMailMessage getSimpleMailMessage()
        {
            return simpleMailMessage;
        }

        public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage)
        {
            Assert.notNull(simpleMailMessage, "simpleMailMessage must be not null");
            this.simpleMailMessage = simpleMailMessage;
        }

        public String getFromPersonal()
        {
            return fromPersonal;
        }

        public void setFromPersonal(String fromPersonal)
        {
            this.fromPersonal = fromPersonal;
        }

        public void prepare(MimeMessage mimeMessage) throws Exception
        {
            simpleMailMessage.copyTo(new MimeMailMessage(mimeMessage));
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, mimeMessage.getEncoding());
            helper.setText(simpleMailMessage.getText(), true);

            if (StringUtils.isNotEmpty(fromPersonal))
            {
                mimeMessage.setFrom(new InternetAddress(simpleMailMessage.getFrom(), fromPersonal));
            }
        }
    }

}

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
package net.jescort.domain.user;

import net.gelif.kernel.core.data.domain.AbstractPersistable;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Email extends AbstractPersistable<EmailPk>
{
    private static final long serialVersionUID = 1L;

    public Email()
    {
    }

    public Email(String emailAddress)
    {
        this.emailPk = new EmailPk(emailAddress);
    }

    private EmailPk emailPk;
    private User user;
    private Integer priority;

    public EmailPk getEmailPk()
    {
        return emailPk;
    }

    public void setEmailPk(EmailPk emailPk)
    {
        this.emailPk = emailPk;
    }



    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Email))
        {
            return false;
        }
        final Email email = (Email) object;
        return emailPk.equals(email.getEmailPk());
    }

    @Override
    public int hashCode()
    {
        return emailPk != null ? emailPk.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return emailPk.toString();
    }
}

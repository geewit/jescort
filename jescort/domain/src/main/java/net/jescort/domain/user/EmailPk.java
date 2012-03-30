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

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class EmailPk implements Serializable
{
    private static final long serialVersionUID = 1L;

    public EmailPk()
    {
    }

    public EmailPk(String email)
    {
        String[] array = email.split("@");
        this.personal = array[0];
        this.hostname = array[1];
    }

    public EmailPk(String personal, String hostname)
    {
        this.personal = personal;
        this.hostname = hostname;
    }

    private String personal;
    private String hostname;

    public String getPersonal()
    {
        return personal;
    }

    public void setPersonal(String personal)
    {
        this.personal = personal;
    }

    public String getHostname()
    {
        return hostname;
    }

    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof EmailPk))
        {
            return false;
        }
        final EmailPk pk = (EmailPk) object;
        return new EqualsBuilder().append(personal, pk.getPersonal()).append(hostname, pk.getHostname()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(personal).append(hostname).toHashCode();
    }

    @Override
    public String toString()
    {
        return personal + "@" + hostname;
    }
}

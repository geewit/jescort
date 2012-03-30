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
package net.jescort.domain.forum;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.enums.MessageStatus;
import net.jescort.domain.user.User;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Message extends AbstractPersistable<Integer> implements Comparable<Message>
{
    private static final long serialVersionUID = 1L;

    public Message()
    {
    }

    private Integer id;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    private User sender;
    private List<User> recipients;
    private Boolean isRead;
    private Map<MessageStatus, Boolean> status;
    private Map<String, String> properties;
    private Calendar createdate;
    private List<Attachment> attachments;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public User getSender()
    {
        return sender;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public List<User> getRecipients()
    {
        return recipients;
    }

    public void setRecipients(List<User> recipients)
    {
        this.recipients = recipients;
    }

    public Boolean getIsRead()
    {
        return isRead;
    }

    public void setIsRead(Boolean isRead)
    {
        this.isRead = isRead;
    }

    public Map<MessageStatus, Boolean> getStatus()
    {
        return status;
    }

    public void setStatus(Map<MessageStatus, Boolean> status)
    {
        this.status = status;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setProperties(Map<String, String> topicProperties)
    {
        this.properties = topicProperties;
    }

    public Calendar getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Calendar createdate)
    {
        this.createdate = createdate;
    }

    public List<Attachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Message))
        {
            return false;
        }
        final Message message = (Message) object;
        return new EqualsBuilder().append(id, message.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("subject", this.subject).append("sender", null != this.sender ? this.sender.getUsername() : null).append("content", this.content).append("isRead", this.isRead).append("createdate", null != this.createdate ? String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", this.createdate) : null).toString();
    }

    @Override
    public int compareTo(Message message)
    {
        return new CompareToBuilder().append(getId(), message.getId()).toComparison();
    }
}

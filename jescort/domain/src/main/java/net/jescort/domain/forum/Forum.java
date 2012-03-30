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
import java.util.Map;
import java.util.Set;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.enums.ForumStatus;
import net.jescort.domain.user.User;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Forum extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;

    public Forum()
    {
    }

    public Forum(Integer id)
    {
        this.id = id;
    }

    private Integer id;
    private String subject;
    private String description;
    private int priority;
    private int topics;
    private int replies;
    private Map<ForumStatus, Boolean> status;
    private Map<String, String> properties;
    private Calendar createdate;
    private Category category;
    private Post lastPost;
    private Set<User> moderators;

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public int getTopics()
    {
        return topics;
    }

    public void setTopics(int topics)
    {
        this.topics = topics;
    }

    public int getReplies()
    {
        return replies;
    }

    public void setReplies(int replies)
    {
        this.replies = replies;
    }

    public Map<ForumStatus, Boolean> getStatus()
    {
        return status;
    }

    public void setStatus(Map<ForumStatus, Boolean> status)
    {
        this.status = status;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }

    public Calendar getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Calendar createdate)
    {
        this.createdate = createdate;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Post getLastPost()
    {
        return lastPost;
    }

    public void setLastPost(Post lastPost)
    {
        this.lastPost = lastPost;
    }

    public Set<User> getModerators()
    {
        return moderators;
    }

    public void setModerators(Set<User> moderators)
    {
        this.moderators = moderators;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Forum))
        {
            return false;
        }
        final Forum forum = (Forum) object;
        return new EqualsBuilder().append(id, forum.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("subject", this.subject).append("description", this.description).append("priority", this.priority).append("createdate", null != this.createdate ? String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", this.createdate) : null).toString();
    }
}

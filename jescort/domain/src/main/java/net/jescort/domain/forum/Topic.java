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

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.enums.TopicStatus;
import org.apache.commons.lang.builder.CompareToBuilder;
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

public class Topic extends AbstractPersistable<Integer> implements Comparable<Topic>
{
    private static final long serialVersionUID = 1L;

    public Topic()
    {
    }

    public Topic(Integer id)
    {
        this.id = id;
    }

    private Integer id;
    private String subject;
    private Integer forumId;
    private Integer rootPostId;
    private Integer lastPostId;
    private Post rootPost;
    private Post lastPost;
    private int views;
    private int replies;
    private Boolean isLocked;
    private Calendar createdate;
    private Map<TopicStatus, Boolean> status;
    private Map<String, String> properties;

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

    public Post getRootPost()
    {
        return rootPost;
    }

    public Integer getRootPostId()
    {
        return rootPostId;
    }

    public void setRootPostId(Integer rootPostId)
    {
        this.rootPostId = rootPostId;
    }

    public Integer getLastPostId()
    {
        return lastPostId;
    }

    public void setLastPostId(Integer lastPostId)
    {
        this.lastPostId = lastPostId;
    }

    public void setRootPost(Post rootPost)
    {
        this.rootPost = rootPost;
    }

    public Post getLastPost()
    {
        return lastPost;
    }

    public void setLastPost(Post lastPost)
    {
        this.lastPost = lastPost;
    }

    public Integer getForumId()
    {
        return forumId;
    }

    public void setForumId(Integer forumId)
    {
        this.forumId = forumId;
    }

    public int getViews()
    {
        return views;
    }

    public void setViews(int views)
    {
        this.views = views;
    }

    public int getReplies()
    {
        return replies;
    }

    public void setReplies(int replies)
    {
        this.replies = replies;
    }

    public Boolean getIsLocked()
    {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked)
    {
        this.isLocked = isLocked;
    }

    public Map<TopicStatus, Boolean> getStatus()
    {
        return status;
    }

    public void setStatus(Map<TopicStatus, Boolean> status)
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

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Topic))
        {
            return false;
        }
        final Topic topic = (Topic) object;
        return new EqualsBuilder().append(id, topic.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("subject", this.subject).append("views", this.views).append("replies", this.replies).toString();
    }

    public int compareTo(Topic topic)
    {
        return new CompareToBuilder().append(getViews(), topic.getViews()).toComparison();
    }
}

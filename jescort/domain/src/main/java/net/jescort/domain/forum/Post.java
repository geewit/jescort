package net.jescort.domain.forum;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.enums.PostStatus;
import net.jescort.domain.user.User;
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

public class Post extends AbstractPersistable<Integer> implements Comparable<Post>
{
    private static final long serialVersionUID = 1L;

    public Post()
    {
    }

    private Integer id;
    private User poster;
    private Topic topic;
    private String content;
    private int edits;
    private Calendar createdate;

    private Map<PostStatus, Boolean> status;
    private Map<String, String> properties;
    private List<Attachment> attachments;
    private PostEdit edit;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public User getPoster()
    {
        return poster;
    }

    public void setPoster(User poster)
    {
        this.poster = poster;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getEdits()
    {
        return edits;
    }

    public void setEdits(int edits)
    {
        this.edits = edits;
    }

    public Calendar getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Calendar createdate)
    {
        this.createdate = createdate;
    }

    public Map<PostStatus, Boolean> getStatus()
    {
        return status;
    }

    public void setStatus(Map<PostStatus, Boolean> status)
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

    public List<Attachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }

    public PostEdit getEdit()
    {
        return edit;
    }

    public void setEdit(PostEdit edit)
    {
        this.edit = edit;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Post))
        {
            return false;
        }
        final Post post = (Post) object;
        return new EqualsBuilder().append(id, post.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("poster", null != this.poster ? (null != this.poster.getUsername() ? this.poster.getUsername() : this.poster.getId()) : null).append("topic", null != this.topic ? (null != this.topic.getSubject() ? this.topic.getSubject() : this.topic.getId()) : null).append("content", this.content).append("edits", this.edits).append("createdate", null != this.createdate ? String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", this.createdate) : null).toString();
    }

    @Override
    public int compareTo(Post post)
    {
        return new CompareToBuilder().append(getCreatedate(), post.getCreatedate()).toComparison();
    }
}
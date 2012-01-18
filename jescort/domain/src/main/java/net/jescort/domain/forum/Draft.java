package net.jescort.domain.forum;

import java.util.Calendar;
import java.util.Map;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
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

public class Draft extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;

    public Draft()
    {
    }

    private Integer id;
    private String subject;
    private String content;
    private Map<String, String> properties;
    private Calendar modification;

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

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }

    public Calendar getModification()
    {
        return modification;
    }

    public void setModification(Calendar modification)
    {
        this.modification = modification;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Draft))
        {
            return false;
        }
        final Draft draft = (Draft) object;
        return new EqualsBuilder().append(id, draft.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("subject", this.subject).append("content", this.content).append("modification", null != this.modification ? String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", this.modification) : null).toString();
    }
}

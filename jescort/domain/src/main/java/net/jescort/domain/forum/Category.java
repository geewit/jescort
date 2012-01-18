package net.jescort.domain.forum;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.enums.CategoryStatus;
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

public class Category extends AbstractPersistable<Integer> implements Comparable<Category>
{
    private static final long serialVersionUID = 1L;

    public Category()
    {
    }

    private Integer id;
    private String subject;
    private String description;
    private Integer priority;
    private Map<CategoryStatus, Boolean> status;
    private Map<String, String> properties;
    private List<Forum> forums;
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

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public Map<CategoryStatus, Boolean> getStatus()
    {
        return status;
    }

    public void setStatus(Map<CategoryStatus, Boolean> status)
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

    public List<Forum> getForums()
    {
        return forums;
    }

    public void setForums(List<Forum> forums)
    {
        this.forums = forums;
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
        if (!(object instanceof Category))
        {
            return false;
        }
        final Category category = (Category) object;
        return new EqualsBuilder().append(id, category.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("subject", this.subject).append("description", this.description).append("priority", this.priority).toString();
    }

    public int compareTo(Category category)
    {
        return new CompareToBuilder().append(this.getPriority(), category.getPriority()).toComparison();
    }
}

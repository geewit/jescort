package net.jescort.domain.user;

import java.util.List;
import java.util.Set;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import org.apache.commons.lang.builder.*;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Group extends AbstractPersistable<Integer> implements Comparable<Group>
{
    private static final long serialVersionUID = 1L;

    public Group()
    {
    }

    private Integer id;
    private String name;
    private String description;
    private Integer priority;
    private Set<Role> roles;
    private Group parent;
    private Set<Group> children;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    public Group getParent()
    {
        return parent;
    }

    public void setParent(Group parent)
    {
        this.parent = parent;
    }

    public Set<Group> getChildren()
    {
        return children;
    }

    public void setChildren(Set<Group> children)
    {
        this.children = children;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Group))
        {
            return false;
        }
        final Group group = (Group) object;
        return new EqualsBuilder().append(name, group.getName()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(name).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("name", this.name).append("description", this.description).append("priority", this.priority).toString();
    }

    @Override
    public int compareTo(Group group)
    {
        return new CompareToBuilder().append(getPriority(), group.getPriority()).toComparison();
    }
}

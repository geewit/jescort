package net.jescort.domain.user;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import org.apache.commons.lang.builder.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Role extends AbstractPersistable<Integer> implements Comparable<Role>
{
    private static final long serialVersionUID = 1L;

    public Role()
    {
    }

    public Role(Integer id, String authority)
    {
        this.id = id;
        this.authority = authority;
    }

    private Integer id;
    private String authority;
    private String description;
    private Integer priority;
    private List<Permission> permissions;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Permission> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions)
    {
        this.permissions = permissions;
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
        if (!(object instanceof Role))
        {
            return false;
        }
        final Role role = (Role) object;
        return new EqualsBuilder().append(authority, role.getAuthority()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("name", this.authority).append("description", this.description).append("priority", this.priority).toString();
    }

    @Override
    public int compareTo(Role role)
    {
        return new CompareToBuilder().append(getPriority(), role.getPriority()).toComparison();
    }
}

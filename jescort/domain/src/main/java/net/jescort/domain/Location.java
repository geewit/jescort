package net.jescort.domain;

import java.util.Set;
import net.gelif.kernel.core.data.domain.AbstractPersistable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.google.gson.annotations.Expose;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Location extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;
    
    public Location()
    {
    }
    
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String abbreviation;
    @Expose
    private Integer level;
    @Expose
    private Boolean available;
    @Expose
    private Location parent;
    @Expose
    private Set<Location> children;
    
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
    
    public String getAbbreviation()
    {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }
    
    public Integer getLevel()
    {
        return level;
    }
    public void setLevel(Integer level)
    {
        this.level = level;
    }
    
    public Boolean getAvailable()
    {
        return available;
    }
    public void setAvailable(Boolean isAvailable)
    {
        this.available = isAvailable;
    }
    
    public Location getParent()
    {
        return parent;
    }
    public void setParent(Location parent)
    {
        this.parent = parent;
    }
    
    public Set<Location> getChildren()
    {
        return children;
    }
    public void setChildren(Set<Location> children)
    {
        this.children = children;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if(this == object)
        {
            return true;
        }
        if(!(object instanceof Location))
        {
            return false;
        }
        final Location location = (Location)object;
        return new EqualsBuilder().append(id, location.getId()).isEquals();
    }
    
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }
    
    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).toString();
    }
}

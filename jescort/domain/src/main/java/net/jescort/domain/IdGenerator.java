package net.jescort.domain;

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

public class IdGenerator extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;
    
    public IdGenerator()
    {
    }
    
    private Integer id;
    
    private String name;
    
    private Integer value;
    
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
    
    public Integer getValue()
    {
        return value;
    }
    public void setValue(Integer value)
    {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if(this == object)
        {
            return true;
        }
        if(!(object instanceof IdGenerator))
        {
            return false;
        }
        final IdGenerator idGenerator = (IdGenerator)object;
        return new EqualsBuilder().append(name, idGenerator.getName()).append(value, idGenerator.getValue()).isEquals();
    }
    
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(name).append(value).toHashCode();
    }
    
    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("name", this.name).append("value", this.value).toString();
    }
}

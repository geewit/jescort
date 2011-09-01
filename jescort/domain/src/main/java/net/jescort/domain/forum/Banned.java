package net.jescort.domain.forum;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.enumerator.BanType;
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

public class Banned extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;
    
    public Banned()
    {
    }
    
    private Integer id;
    private BanType key;
    private String value;
    
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public BanType getKey()
    {
        return key;
    }
    public void setKey(BanType key)
    {
        this.key = key;
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
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
        if(!(object instanceof Banned))
        {
            return false;
        }
        final Banned banned = (Banned)object;
        return new EqualsBuilder().append(id, banned.getId()).isEquals();
    }
    
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }
    
    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("key", this.key).append("value", this.value).toString();
    }
}

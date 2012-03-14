package net.jescort.domain.user;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.Location;
import net.jescort.domain.enums.AddressType;
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

public class Address extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;

    public Address()
    {
    }

    private Integer id;
    private User user;
    private String postalcode;
    private String street;
    private Location location;
    private Integer priority;
    private AddressType type;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getPostalcode()
    {
        return postalcode;
    }

    public void setPostalcode(String postalcode)
    {
        this.postalcode = postalcode;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String address)
    {
        this.street = address;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public AddressType getType()
    {
        return type;
    }

    public void setType(AddressType type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Address))
        {
            return false;
        }
        final Address address = (Address) object;
        return new EqualsBuilder().append(id, address.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("location", null != this.location ? this.location.getId() : null).append("postalcode", this.postalcode).append("street", this.street).toString();
    }
}

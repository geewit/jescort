package net.jescort.domain.user;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Name implements Serializable
{
    private static final long serialVersionUID = 1L;

    public Name()
    {
    }

    public Name(String famaliyName, String givenName)
    {
        this.famaliyName = famaliyName;
        this.givenName = givenName;
    }

    private String famaliyName;
    private String givenName;

    public String getFamaliyName()
    {
        return famaliyName;
    }

    public void setFamaliyName(String famaliyName)
    {
        this.famaliyName = famaliyName;
    }

    public String getGivenName()
    {
        return givenName;
    }

    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Name))
        {
            return false;
        }
        final Name name = (Name) object;
        return new EqualsBuilder().append(famaliyName, name.getFamaliyName()).append(givenName, name.getGivenName()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(famaliyName).append(givenName).toHashCode();
    }

    @Override
    public String toString()
    {
        return famaliyName + " " + givenName;
    }
}

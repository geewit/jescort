package net.jescort.domain.user;

import net.gelif.kernel.core.util.DateUtils;
import net.jescort.domain.enums.Gender;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-31
 * Time: 上午10:45
 */
public class Profile implements Serializable
{
    private static final long serialVersionUID = 1L;

    public Profile()
    {
    }

    private Integer id;

    private Gender gender;

    private Calendar birthday;

    private byte[] photo;

    private String signature;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Calendar getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Calendar birthday)
    {
        this.birthday = birthday;
    }

    public byte[] getPhoto()
    {
        return photo;
    }

    public void setPhoto(byte[] photo)
    {
        this.photo = photo;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }

    public int getAge()
    {
        return DateUtils.getAge(this.getBirthday());
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Profile))
        {
            return false;
        }
        final Profile profile = (Profile) object;
        return new EqualsBuilder().append(id, profile.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id);
        return sb.toString();
    }
}

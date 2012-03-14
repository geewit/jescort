package net.jescort.domain.user;

import java.util.*;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import org.apache.commons.lang.StringUtils;
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

public class User extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;

    public User()
    {
    }

    public User(Integer id)
    {
        this.id = id;
    }

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer posts;
    private Integer reputation;
    private String timezone;
    private Locale locale;
    private Calendar createdate;
    private Calendar lastActive;
    private Profile profile;
    private List<Email> emails = new ArrayList<Email>();
    private List<Address> addresses = new ArrayList<Address>();
    private Set<Group> groups = new HashSet<Group>();
    private Map<String, String> properties;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNickname()
    {
        return StringUtils.isNotBlank(nickname) ? nickname : username;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public Integer getPosts()
    {
        return posts;
    }

    public void setPosts(Integer posts)
    {
        this.posts = posts;
    }

    public Integer getReputation()
    {
        return reputation;
    }

    public void setReputation(Integer reputation)
    {
        this.reputation = reputation;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }

    public Calendar getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Calendar createdate)
    {
        this.createdate = createdate;
    }

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public Calendar getLastActive()
    {
        return lastActive;
    }

    public void setLastActive(Calendar lastActive)
    {
        this.lastActive = lastActive;
    }

    public List<Email> getEmails()
    {
        return emails;
    }

    public void setEmails(List<Email> emails)
    {
        this.emails = emails;
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

    public Set<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(Set<Group> groups)
    {
        this.groups = groups;
    }

    public Set<Role> getRoles()
    {
        Set<Role> roles = new HashSet<Role>();
        for(Group group : getGroups())
        {
            roles.addAll(group.getRoles());
        }
        return roles;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }

    public Address getMainAddress()
    {
        List<Address> addresses = this.getAddresses();
        Address address = null;
        if (null != addresses)
        {
            address = addresses.get(0);
        }
        return address;
    }

    public Email getMainEmail()
    {
        List<Email> emails = this.getEmails();
        Email email = null;
        if (null != emails && !emails.isEmpty())
        {
            email = emails.get(0);
        }
        return email;
    }

    public Group getMainGroup()
    {
        Group group = null;
        Set<Group> groupSet = this.getGroups();
        if (null != groupSet && !groupSet.isEmpty())
        {
            List<Group> groups = new LinkedList<Group>(groupSet);
            Collections.sort(groups);
            group = groups.get(0);
        }
        return group;
    }

    public float getPostsPerday()
    {
        Calendar now = Calendar.getInstance();
        long nowMillis = now.getTimeInMillis();
        long createdateMillis = createdate.getTimeInMillis();
        long days = (createdateMillis - nowMillis) / (24 * 60 * 60 * 1000);
        float postsPerday = this.getPosts() / days;
        return postsPerday;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof User))
        {
            return false;
        }
        final User user = (User) object;
        return new EqualsBuilder().append(username, user.getUsername()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("username", this.username).append("password", this.password).append("nickname", this.nickname).append("posts", this.posts).append("reputation", this.reputation).append("timezone", this.timezone).append("createdate", null != this.createdate ? String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", this.createdate) : null);
        Set<Group> groups = getGroups();
        if (groups != null)
        {
            sb.append("groups: ");
            for(Group group : groups)
            {
                sb.append(group.getName());
                sb.append(", ");
            }
        } else
        {
            sb.append("No Groups");
        }
        return sb.toString();
    }
}

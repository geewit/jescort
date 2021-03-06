/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.domain.user;

import java.util.*;

import com.google.gson.annotations.Expose;
import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.gelif.kernel.core.util.DateUtils;
import net.jescort.domain.enums.Gender;
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

public class User extends AbstractPersistable<String>
{
    private static final long serialVersionUID = 1L;

    public User()
    {
    }

    public User(String id)
    {
        this.id = id;
    }

    @Expose
    private String id;
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String nickname;
    @Expose
    private Integer posts;
    @Expose
    private Integer reputation;
    @Expose
    private Gender gender;
    @Expose
    private Name name;
    @Expose
    private Calendar birthday;
    @Expose
    private String avatar;
    @Expose
    private String signature;
    @Expose
    private String timezone;
    @Expose
    private Locale locale;
    @Expose
    private Calendar createdate;
    @Expose
    private Calendar lastActive;
    @Expose
    private List<Email> emails = new ArrayList<Email>();
    @Expose
    private List<Address> addresses = new ArrayList<Address>();
    @Expose
    private Set<Group> groups = new HashSet<Group>();
    @Expose
    private Map<String, String> properties;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Name getName()
    {
        return name;
    }

    public void setName(Name name)
    {
        this.name = name;
    }

    public Calendar getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Calendar birthday)
    {
        this.birthday = birthday;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
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
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("username", this.username).append("password", this.password).append("nickname", this.nickname).append("name", null != this.name ? this.name.toString() : null).append("avatar", this.avatar).append("posts", this.posts).append("reputation", this.reputation).append("timezone", this.timezone).append("createdate", null != this.createdate ? String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", this.createdate) : null);
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

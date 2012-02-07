package net.jescort.web.servlet.controller.auth;

import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-1-29
 * Time: 下午2:17
 */
public class ProfileCommand
{
    private String username;
    private String famaliyName;
    private String givenName;
    private String nickname;
    private String locale;
    private String timezone;
    private String gender;
    private Calendar birthday;
    private byte[] avatar;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

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

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
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

    public byte[] getAvatar()
    {
        return avatar;
    }

    public void setAvatar(byte[] avatar)
    {
        this.avatar = avatar;
    }
}

package net.jescort.domain.user;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-18
 * Time: 上午9:15
 */
public class ShiroUser  implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String nickname;
    private String avatar;

    public ShiroUser(String id, String username, String nickname, String avatar)
    {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public String getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getAvatar()
    {
        return avatar;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString()
    {
        return username;
    }
}

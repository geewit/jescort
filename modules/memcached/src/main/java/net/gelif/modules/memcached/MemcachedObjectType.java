package net.gelif.modules.memcached;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-12
 * Time: 下午1:10
 */
public enum MemcachedObjectType
{
    USER("user:", 60 * 60 * 1);

    private String prefix;
    private int expiredTime;

    MemcachedObjectType(String prefix, int expiredTime)
    {
        this.prefix = prefix;
        this.expiredTime = expiredTime;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public int getExpiredTime()
    {
        return expiredTime;
    }

}

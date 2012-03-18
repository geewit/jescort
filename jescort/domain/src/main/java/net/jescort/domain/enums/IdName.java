package net.jescort.domain.enums;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-14
 * Time: 下午9:25
 */
public enum IdName
{
    ATTACHMENT("attachment_id"), ADDRESS("address_id"), POST("post_id"), TOPIC("topic_id");

    IdName(String idName)
    {
        this.idName = idName;
    }

    public String getIdName()
    {
        return idName;
    }

    private final String idName;
}

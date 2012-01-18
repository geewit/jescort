package net.jescort.domain.enums;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */
public enum IdName
{
    USER("user_id"), ATTACHMENT("attachment_id"), ADDRESS("address_id"), POST("post_id"), TOPIC("topic_id");

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

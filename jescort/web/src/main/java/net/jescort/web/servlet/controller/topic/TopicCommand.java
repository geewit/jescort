package net.jescort.web.servlet.controller.topic;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-15
 * Time: 下午5:34
 */
public class TopicCommand
{
    @NotBlank
    private String subject;

    @NotBlank
    private String content;

    private Integer forumId;

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getForumId()
    {
        return forumId;
    }

    public void setForumId(Integer forumId)
    {
        this.forumId = forumId;
    }
}

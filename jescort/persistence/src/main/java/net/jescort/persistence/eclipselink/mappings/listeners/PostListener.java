package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.Calendar;
import java.util.EventListener;

import net.jescort.domain.forum.Post;

public class PostListener implements EventListener
{
    public void prePersist(Post post)
    {
        if (null == post)
        {
            return;
        }
        if (null == post.getCreatedate())
        {
            post.setCreatedate(Calendar.getInstance());
        }
    }
}

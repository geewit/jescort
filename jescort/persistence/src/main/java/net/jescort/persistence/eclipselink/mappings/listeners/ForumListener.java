package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.Calendar;
import java.util.EventListener;

import net.jescort.domain.forum.Forum;

public class ForumListener implements EventListener
{
    //@PrePersist
    public void prePersist(Forum forum)
    {
        if (null == forum)
        {
            return;
        }
        if (null == forum.getCreatedate())
        {
            forum.setCreatedate(Calendar.getInstance());
        }
    }
}

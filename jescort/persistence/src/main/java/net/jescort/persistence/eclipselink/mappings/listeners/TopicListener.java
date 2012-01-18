package net.jescort.persistence.eclipselink.mappings.listeners;

import net.jescort.domain.forum.Topic;

import java.util.Calendar;
import java.util.EventListener;

public class TopicListener implements EventListener
{
    public void prePersist(Topic topic)
    {
        if (null == topic)
        {
            return;
        }
        if (null == topic.getCreatedate())
        {
            topic.setCreatedate(Calendar.getInstance());
        }
    }
}

package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.Calendar;
import java.util.EventListener;

import net.jescort.domain.forum.Message;

public class MessageListener implements EventListener
{
    public void prePersist(Message message)
    {
        if (null == message)
        {
            return;
        }
        if (null == message.getCreatedate())
        {
            message.setCreatedate(Calendar.getInstance());
        }
    }
}

package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.Calendar;
import java.util.EventListener;

import net.jescort.domain.forum.Attachment;

public class AttachmentListener implements EventListener
{
    //@PrePersist
    public void prePersist(Attachment attachment)
    {
        if (null == attachment)
        {
            return;
        }
        if (null == attachment.getDownloads())
        {
            attachment.setDownloads(0);
        }
        if (null == attachment.getCreatedate())
        {
            attachment.setCreatedate(Calendar.getInstance());
        }
    }
}

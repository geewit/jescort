package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.Calendar;
import java.util.EventListener;

import net.jescort.domain.forum.Draft;

public class DraftListener implements EventListener
{
    //@PrePersist
    //@PreUpdate
    public void prePersist(Draft draft)
    {
        if(null == draft)
        {
            return;
        }
        draft.setModification(Calendar.getInstance());
    }
}

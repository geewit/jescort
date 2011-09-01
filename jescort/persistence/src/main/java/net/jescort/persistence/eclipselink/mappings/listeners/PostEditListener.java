package net.jescort.persistence.eclipselink.mappings.listeners;

import net.jescort.domain.forum.Post;
import net.jescort.domain.forum.PostEdit;

import java.util.Calendar;
import java.util.EventListener;

public class PostEditListener implements EventListener
{
    public void prePersist(PostEdit postEdit)
    {
        updateDate(postEdit);
    }

    public void preUpdate(PostEdit postEdit)
    {
        updateDate(postEdit);
    }

    private void updateDate(PostEdit postEdit)
    {
        if(null == postEdit)
        {
            return;
        }
        if(null == postEdit.getEditdate())
        {
            postEdit.setEditdate(Calendar.getInstance());
        }
    }
}

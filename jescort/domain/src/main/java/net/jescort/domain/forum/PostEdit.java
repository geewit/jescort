package net.jescort.domain.forum;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.user.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-17
 * Time: 下午2:51
 */
public class PostEdit extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private User editor;
    private Calendar editdate;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public User getEditor()
    {
        return editor;
    }

    public void setEditor(User editor)
    {
        this.editor = editor;
    }

    public Calendar getEditdate()
    {
        return editdate;
    }

    public void setEditdate(Calendar editdate)
    {
        this.editdate = editdate;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("editor", null != this.editor ? (null != this.editor.getUsername() ? this.editor.getUsername() : this.editor.getId()) : null).append("editdate", null != this.editdate ? String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", this.editdate) : null).toString();
    }
}

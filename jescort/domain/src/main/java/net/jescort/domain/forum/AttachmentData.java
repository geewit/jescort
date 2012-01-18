package net.jescort.domain.forum;

import net.gelif.kernel.core.util.FileUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.File;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-31
 * Time: 下午5:48
 */
public class AttachmentData implements Serializable
{
    private static final long serialVersionUID = 1L;

    public AttachmentData()
    {
    }

    private Integer id;
    private byte[] content;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public byte[] getContent()
    {
        return content;
    }

    public void setContent(byte[] content)
    {
        this.content = content;
    }

    public File getFile(String outputFile)
    {
        return FileUtils.getFile(content, outputFile);
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof AttachmentData))
        {
            return false;
        }
        final AttachmentData attachmentData = (AttachmentData) object;
        return new EqualsBuilder().append(id, attachmentData.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).toString();
    }
}

package net.jescort.domain.forum;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

import net.gelif.kernel.core.config.Properties;
import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.gelif.kernel.core.util.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Attachment extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;

    public Attachment()
    {
    }

    private Integer id;
    private String originalName;
    private String contentType;
    private long size;
    private Map<String, String> properties;
    private Integer downloads;
    private Calendar createdate;
    private AttachmentData attachmentData;

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getOriginalName()
    {
        return originalName;
    }
    public void setOriginalName(String originalName)
    {
        this.originalName = originalName;
    }

    public String getContentType()
    {
        return contentType;
    }
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public long getSize()
    {
        return this.size;
    }
    public void setSize(long size)
    {
        this.size = size;
    }

    public Integer getDownloads()
    {
        return downloads;
    }

    public void setDownloads(Integer downloads)
    {
        this.downloads = downloads;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }
    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }

    public Calendar getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(Calendar createdate)
    {
        this.createdate = createdate;
    }

    public AttachmentData getAttachmentData()
    {
        return attachmentData;
    }

    public void setAttachmentData(AttachmentData attachmentData)
    {
        this.attachmentData = attachmentData;
    }

    public boolean isAllowed()
    {
        return ArrayUtils.contains(StringUtils.split(Properties.getInstance().getConfig().getString("content.extension"), ","), contentType);
    }

    public float getKiloBytes()
    {
        return this.getSize() / 1024f;
    }

    public float getMegaBytes()
    {
        return this.getSize() / (1024 * 1024);
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Attachment))
        {
            return false;
        }
        final Attachment attachment = (Attachment) object;
        return new EqualsBuilder().append(id, attachment.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("originalName", this.originalName).append("contentType", this.contentType).append("size", this.size).toString();
    }
}

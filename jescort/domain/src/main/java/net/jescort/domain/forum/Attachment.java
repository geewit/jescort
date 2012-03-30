/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.domain.forum;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

import net.gelif.kernel.core.config.JescortConfig;
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
    private String ownerId;
    private String originalName;
    private String contentType;
    private byte[] content;
    private long size;
    private Map<String, String> properties;
    private Integer downloads;
    private Calendar createdate;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(String ownerId)
    {
        this.ownerId = ownerId;
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

    public byte[] getContent()
    {
        return content;
    }

    public void setContent(byte[] content)
    {
        this.content = content;
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

    public File getFile(String outputFile)
    {
        return FileUtils.getFile(content, outputFile);
    }

    public boolean isAllowed()
    {
        return ArrayUtils.contains(StringUtils.split(JescortConfig.getProperty("content.extension"), ","), contentType);
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

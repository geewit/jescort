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

import net.gelif.kernel.core.data.domain.AbstractPersistable;
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

public class Emoticon extends AbstractPersistable<Integer>
{
    private static final long serialVersionUID = 1L;

    public Emoticon()
    {
    }

    private Integer id;
    private String emoticon;
    private String image;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getEmoticon()
    {
        return emoticon;
    }

    public void setEmoticon(String emoticon)
    {
        this.emoticon = emoticon;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Emoticon))
        {
            return false;
        }
        final Emoticon emoticon = (Emoticon) object;
        return new EqualsBuilder().append(id, emoticon.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("emoticon", this.emoticon).append("image", this.image).toString();
    }
}

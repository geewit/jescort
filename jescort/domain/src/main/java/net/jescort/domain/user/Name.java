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
package net.jescort.domain.user;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Name implements Serializable
{
    private static final long serialVersionUID = 1L;

    public Name()
    {
    }

    public Name(String famaliyName, String givenName)
    {
        this.famaliyName = famaliyName;
        this.givenName = givenName;
    }
    
    public final static Name EMPTY_NAME = new Name("", "");

    private String famaliyName;
    private String givenName;

    public String getFamaliyName()
    {
        return famaliyName;
    }

    public void setFamaliyName(String famaliyName)
    {
        this.famaliyName = famaliyName;
    }

    public String getGivenName()
    {
        return givenName;
    }

    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Name))
        {
            return false;
        }
        final Name name = (Name) object;
        return new EqualsBuilder().append(famaliyName, name.getFamaliyName()).append(givenName, name.getGivenName()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(famaliyName).append(givenName).toHashCode();
    }

    @Override
    public String toString()
    {
        return famaliyName + " " + givenName;
    }
}

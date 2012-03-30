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
package net.gelif.kernel.core.data.domain;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;

/**
 * @author admin@gelif.net
 */
@SuppressWarnings("serial")
public abstract class AbstractPersistable<PK extends Serializable> implements Persistable<PK>
{
    private PK id;

    /*
    * (non-Javadoc)
    *
    * @see org.springframework.data.domain.Persistable#getId()
    */
    public PK getId()
    {
        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    protected void setId(final PK id)
    {
        this.id = id;
    }

    /*
    * (non-Javadoc)
    *
    * @see org.springframework.data.domain.Persistable#isNew()
    */
    public boolean isNew()
    {
        return null == getId();
    }
}

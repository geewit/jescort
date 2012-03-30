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
package net.jescort.persistence.dao.jpa;

import net.gelif.kernel.persistence.dao.jpa.GenericJpaDao;
import net.jescort.domain.forum.Emoticon;
import net.jescort.persistence.dao.EmoticonDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository("emoticonDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class EmoticonJpaDao extends GenericJpaDao<Emoticon, Integer> implements EmoticonDao
{
    public Emoticon findByEmoticon(String emoticon)
    {
        return (Emoticon) entityManager.createQuery("SELECT t FROM  Emoticon t WHERE t.emoticon = :emoticon").setParameter("emoticon", emoticon).getSingleResult();
    }
}

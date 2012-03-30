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
package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.*;

import net.jescort.domain.user.Group;
import net.jescort.domain.user.Role;
import net.jescort.domain.user.User;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserListener implements EventListener
{
    private transient final Log logger = LogFactory.getLog(UserListener.class);

    public void prePersist(User user)
    {
        if (null == user)
        {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        if (null == user.getCreatedate())
        {
            user.setCreatedate(calendar);
        }
        if (null == user.getLastActive())
        {
            user.setLastActive(calendar);
        }
        if(null == user.getPosts())
        {
            user.setPosts(0);
        }
        if(null == user.getReputation())
        {
            user.setReputation(0);
        }

    }

    public void postLoad(User user)
    {
        if (null == user)
        {
            return;
        }
        if (null == user.getGroups())
        {
            return;
        } else
        {
            Set<Role> roles = new HashSet<Role>();

            Stack<Group> stack = new Stack<Group>();
            for(Group root : user.getGroups())
            {
                stack.push(root);
                while (!stack.empty())
                {
                    Group group = stack.pop();
                    roles.addAll(group.getRoles());

                    Set<Group> children = group.getChildren();

                    if (children != null && !children.isEmpty())
                    {
                        for(Group child : children)
                        {
                            stack.push(child);
                        }
                    }
                }
            }
        }
    }
}

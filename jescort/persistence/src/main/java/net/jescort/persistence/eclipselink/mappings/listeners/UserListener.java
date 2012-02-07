package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.*;

import net.jescort.domain.user.Group;
import net.jescort.domain.user.Name;
import net.jescort.domain.user.Role;
import net.jescort.domain.user.User;

public class UserListener implements EventListener
{
    //private transient final Log logger = LogFactory.getLog(UserListener.class);
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

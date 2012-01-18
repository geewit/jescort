package net.jescort.persistence.eclipselink.mappings.listeners;

import java.util.*;

import net.jescort.domain.user.Group;
import net.jescort.domain.user.Role;
import net.jescort.domain.user.User;

public class UserListener implements EventListener
{
    public void prePersist(User user)
    {
        if (null == user)
        {
            return;
        }
        if (null == user.getCreatedate())
        {
            user.setCreatedate(Calendar.getInstance());
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
                roles.addAll(root.getRoles());
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

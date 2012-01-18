package net.jescort.domain.user;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import org.apache.shiro.util.CollectionUtils;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Permission extends AbstractPersistable<Integer> implements org.apache.shiro.authz.Permission
{
    private static final long serialVersionUID = 1L;

    public Permission()
    {
    }

    private static final String WILDCARD_TOKEN = "*";
    private static final String PART_DIVIDER_TOKEN = ":";
    private static final String SUBPART_DIVIDER_TOKEN = ",";
    private static final boolean DEFAULT_CASE_SENSITIVE = false;

    private Integer id;
    private String stringPermission;

    private List<Set<String>> parts;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getStringPermission()
    {
        return stringPermission;
    }

    public void setStringPermission(String stringPermission)
    {
        this.stringPermission = stringPermission;
    }

    protected void setParts()
    {
        setParts(DEFAULT_CASE_SENSITIVE);
    }

    protected void setParts(boolean caseSensitive)
    {
        if (stringPermission == null || stringPermission.trim().length() == 0)
        {
            throw new IllegalArgumentException("Wildcard string cannot be null or empty. Make sure permission strings are properly formatted.");
        }

        stringPermission = stringPermission.trim();

        List<String> parts = CollectionUtils.asList(stringPermission.split(PART_DIVIDER_TOKEN));

        this.parts = new ArrayList<Set<String>>();
        for(String part : parts)
        {
            Set<String> subparts = CollectionUtils.asSet(part.split(SUBPART_DIVIDER_TOKEN));
            if (!caseSensitive)
            {
                subparts = lowercase(subparts);
            }
            if (subparts.isEmpty())
            {
                throw new IllegalArgumentException("Wildcard string cannot contain parts with only dividers. Make sure permission strings are properly formatted.");
            }
            this.parts.add(subparts);
        }

        if (this.parts.isEmpty())
        {
            throw new IllegalArgumentException("Wildcard string cannot contain only dividers. Make sure permission strings are properly formatted.");
        }
    }

    private Set<String> lowercase(Set<String> subparts)
    {
        Set<String> lowerCasedSubparts = new LinkedHashSet<String>(subparts.size());
        for(String subpart : subparts)
        {
            lowerCasedSubparts.add(subpart.toLowerCase());
        }
        return lowerCasedSubparts;
    }

    private List<Set<String>> getParts()
    {
        return this.parts;
    }

    @Override
    public boolean implies(org.apache.shiro.authz.Permission p)
    {
        // By default only supports comparisons with other WildcardPermissions
        if (!(p instanceof Permission))
        {
            return false;
        }

        Permission permission = (Permission) p;

        List<Set<String>> otherParts = permission.getParts();

        int i = 0;
        for(Set<String> otherPart : otherParts)
        {
            // If this permission has less parts than the other permission, everything after the number of parts contained
            // in this permission is automatically implied, so return true
            if (getParts().size() - 1 < i)
            {
                return true;
            } else
            {
                Set<String> part = getParts().get(i);
                if (!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart))
                {
                    return false;
                }
                i++;
            }
        }

        // If this permission has more parts than the other parts, only imply it if all of the other parts are wildcards
        for(; i < getParts().size(); i++)
        {
            Set<String> part = getParts().get(i);
            if (!part.contains(WILDCARD_TOKEN))
            {
                return false;
            }
        }

        return true;
    }
}

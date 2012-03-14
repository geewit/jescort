package net.jescort.persistence.dao;

import net.jescort.domain.enums.IdName;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-14
 * Time: 下午9:24
 */
public interface IdGeneratorDao
{
    public Integer newId(IdName idName);
}

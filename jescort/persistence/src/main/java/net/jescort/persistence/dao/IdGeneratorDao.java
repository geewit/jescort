package net.jescort.persistence.dao;


import net.jescort.domain.enumerator.IdName;

public interface IdGeneratorDao
{
    public Integer newId(IdName idName);
}

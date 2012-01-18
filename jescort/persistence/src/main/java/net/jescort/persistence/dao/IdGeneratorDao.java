package net.jescort.persistence.dao;


import net.jescort.domain.enums.IdName;

public interface IdGeneratorDao
{
    public Integer newId(IdName idName);
}

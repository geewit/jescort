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

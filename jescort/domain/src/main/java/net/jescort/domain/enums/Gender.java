package net.jescort.domain.enums;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-7
 * Time: 下午10:25
 */
public enum Gender
{
    MALE(true), FEMALE(false);

    Gender(Boolean isMale)
    {
        this.isMale = isMale;
    }

    public Boolean getIsMale()
    {
        return isMale;
    }

    private final Boolean isMale;
}

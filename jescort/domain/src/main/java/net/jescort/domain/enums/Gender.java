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

    Gender(boolean isMale)
    {
        this.isMale = isMale;
    }

    public boolean getIsMale()
    {
        return isMale;
    }

    private final boolean isMale;
}

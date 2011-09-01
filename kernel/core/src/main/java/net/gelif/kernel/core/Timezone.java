package net.gelif.kernel.core;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-11
 * Time: 下午10:22
 */
public class Timezone implements Serializable
{
    private static final long serialVersionUID = 1L;

    public Timezone(String locale, String offset)
    {
        this.locale = locale;
        this.offset = offset;
    }

    private String locale;
    private String offset;



    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    public String getOffset()
    {
        return offset;
    }

    public void setOffset(String offset)
    {
        this.offset = offset;
    }
}

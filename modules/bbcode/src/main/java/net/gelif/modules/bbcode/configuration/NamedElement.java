package net.gelif.modules.bbcode.configuration;

import net.gelif.modules.bbcode.Util;

/**
 * Named element definition
 * 
 * @author Vitaliy Samolovskih aka Kefir
 */
public class NamedElement
{
    protected String name;
    
    /**
     * Create named element with random name
     */
    public NamedElement()
    {
        name = Util.generateRandomName();
    }
    
    public NamedElement(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}

package net.gelif.modules.bbcode.configuration;

import net.gelif.modules.bbcode.WNamedValue;
import net.gelif.modules.bbcode.WTemplateElement;

/**
 * @author Vitaliy Samolovskih aka Kefir
 */
public class NamedValue extends NamedElement implements TemplateElement
{

    public NamedValue(String name)
    {
        super(name);
    }

    public WTemplateElement create()
    {
        return new WNamedValue(name);
    }
}

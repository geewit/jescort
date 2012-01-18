package net.gelif.modules.bbcode.configuration;

import net.gelif.modules.bbcode.AbstractCode;
import net.gelif.modules.bbcode.WPatternElement;
import net.gelif.modules.bbcode.WScope;
import net.gelif.modules.bbcode.WText;

import java.util.Map;

/**
 * @author Vitaliy Samolovskih aka Kefir
 */
public class Text extends NamedElement implements PatternElement
{
    private final String scope;
    private final boolean transparent;

    public Text(String name, String scope, boolean transparent)
    {
        super(name);
        this.scope = scope;
        this.transparent = transparent;
    }

    public String getScope()
    {
        return scope;
    }

    public boolean isTransparent()
    {
        return transparent;
    }

    public WPatternElement create(Configuration configuration, Map<Scope, WScope> scopes, Map<Code, AbstractCode> codes)
    {
        if (scope != null)
        {
            return new WText(getName(), configuration.getScope(scope).create(configuration, scopes, codes), isTransparent());
        } else
        {
            return new WText(getName(), isTransparent());
        }
    }
}

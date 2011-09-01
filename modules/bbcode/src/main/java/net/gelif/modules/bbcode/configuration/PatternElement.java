package net.gelif.modules.bbcode.configuration;

import net.gelif.modules.bbcode.AbstractCode;
import net.gelif.modules.bbcode.WPatternElement;
import net.gelif.modules.bbcode.WScope;

import java.util.Map;

/**
 * @author Vitaliy Samolovskih aka Kefir
 */
public interface PatternElement
{
    WPatternElement create(Configuration configuration, Map<Scope, WScope> createdScopes, Map<Code, AbstractCode> codes);
}

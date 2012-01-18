package net.gelif.modules.bbcode;

/**
 * The element of template to build target text
 */
public interface WTemplateElement
{
    /**
     * Append template element to source of context
     *
     * @param context контекст
     * @return builded text
     */
    public CharSequence generate(Context context);
}

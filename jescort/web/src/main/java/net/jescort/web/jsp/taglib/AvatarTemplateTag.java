package net.jescort.web.jsp.taglib;


import net.gelif.kernel.core.config.JescortConfig;
import net.gelif.kernel.core.util.FilepathUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-16
 * Time: 下午10:30
 */
public class AvatarTemplateTag extends TagSupport implements TryCatchFinally
{
    private transient final static Log logger = LogFactory.getLog(AvatarTemplateTag.class);

    private static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE = "org.springframework.web.servlet.tags.REQUEST_CONTEXT";

    private RequestContext requestContext;

    private String avatar;
    private String var = null;

    private static final String avatarPrefixPath = JescortConfig.getProperty("settings.avatar.prefix.path");

    private static final String defaultAvatarPath = JescortConfig.getProperty("settings.avatar.default.path");

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public void setVar(String var)
    {
        this.var = var;
    }

    @Override
    public int doStartTag() throws JspException
    {
        this.requestContext = (RequestContext) this.pageContext.getAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE);
        if (null == this.requestContext)
        {
            this.pageContext.setAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE, this.requestContext);
        }
        String out = writeHtml();
        logger.debug("out == " + out);
        if (null == this.var)
        {
            try
            {
                pageContext.getOut().write(out);
                return EVAL_BODY_INCLUDE;
            }
            catch (IOException e)
            {
                throw new JspException(e);
            }
        }
        else
        {
            // store the url as a variable
            pageContext.setAttribute(var, out, PageContext.PAGE_SCOPE);
            return EVAL_PAGE;
        }

    }

    public void doCatch(Throwable throwable) throws Throwable
    {
        throw throwable;
    }

    public void doFinally()
    {
        this.requestContext = null;
    }

    private String writeHtml()
    {
        logger.debug("avatar == " + avatar);
        StringBuffer sb = new StringBuffer();
        if(StringUtils.isBlank(avatar) || !(avatar.length() > 32))
        {
            sb.append(defaultAvatarPath);
        }
        else
        {
            String contextPath = requestContext.getContextPath();
            sb.append(contextPath);
            sb.append(FilepathUtils.convertSeparator(FilepathUtils.filenameTofullFilepath(avatarPrefixPath, avatar)));
        }
        return sb.toString();
    }
}

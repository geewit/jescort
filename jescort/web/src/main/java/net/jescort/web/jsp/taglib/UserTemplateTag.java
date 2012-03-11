package net.jescort.web.jsp.taglib;

import net.jescort.domain.user.Group;
import net.jescort.domain.user.User;
import org.springframework.web.servlet.support.JspAwareRequestContext;
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
public class UserTemplateTag extends TagSupport implements TryCatchFinally
{
    private static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE = "org.springframework.web.servlet.tags.REQUEST_CONTEXT";
    private RequestContext requestContext;

    private User user;
    private String var = null;

    public void setUser(User user)
    {
        this.user = user;
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
            this.requestContext = new JspAwareRequestContext(this.pageContext);
            this.pageContext.setAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE, this.requestContext);
        }
        String html = writeHtml();
        if (null == this.var)
        {
            try
            {
                pageContext.getOut().write(html);
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
            pageContext.setAttribute(var, html, PageContext.PAGE_SCOPE);
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
        String contextPath = requestContext.getContextPath();
        StringBuffer sb = new StringBuffer();
        Group mainGroup = user.getMainGroup();
        if(Integer.valueOf(4).equals(mainGroup.getId()))
        {
            sb.append("<img alt=\"vet\" style=\"vertical-align:middle;\" src=\"");
            sb.append(contextPath).append("/static/images/vetstar.gif\"/>");
        }
        sb.append("<span class=\"").append(user.getMainGroup().getName().toLowerCase()).append("\">");
        if(mainGroup.getId() == 2 || mainGroup.getId() == 3)
        {
            sb.append("+");
        }
        sb.append(user.getNickname());
        sb.append("</span>");
        return sb.toString();
    }
}

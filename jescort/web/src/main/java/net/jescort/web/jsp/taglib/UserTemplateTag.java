package net.jescort.web.jsp.taglib;

import net.jescort.domain.forum.Attachment;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.ExpressionEvaluationUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.IOException;
import java.util.Collection;

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

    private Collection<Attachment> attachments;

    public void setAttachments(Collection<Attachment> attachments)
    {
        this.attachments = attachments;
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

        try
        {
            pageContext.getOut().write(writeHtml());
            return EVAL_BODY_INCLUDE;
        } catch (IOException io)
        {
            throw new JspException(io);
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

    private String writeHtml() throws JspException
    {
        String contextPath = requestContext.getContextPath();
        StringBuffer sb = new StringBuffer();
        sb.append("<div class=\"rounded clearfix\" id=\"attach_wrap\">");
        sb.append("<h4><spring:message code=\"message.attachment\"/></h4>\n");
        sb.append("<ul>\n");
        for(Attachment attachment : attachments)
        {
            sb.append("<li class=\"clear\">\n");
            sb.append("<a title=\"Download attachment\" href=\"");
            sb.append(contextPath).append("/attachments/").append(attachment.getId());
            sb.append("\"><img alt=\"Attached File\" src=\"");
            sb.append(contextPath).append("/static/images/zip.gif\"></a>\n");
            sb.append("&nbsp;<a title=\"Download attachment\" href=\"");
            sb.append(contextPath).append("/attachments/").append(attachment.getId());
            sb.append("\">").append(attachment.getOriginalName());
            sb.append("</a><span class=\"desc\"><strong>(").append(attachment.getKiloBytes()).append("KB)</strong></span>\n");
            sb.append("<br><span class=\"desc info\">").append(resolveMessage("message.number_of_downloads", StringUtils.EMPTY)).append(attachment.getDownloads()).append("</span>\n");
            sb.append("</li>\n");
        }
        sb.append("</ul>\n");
        sb.append("</div>");

        return sb.toString();
    }

    private String resolveMessage(String messageCode, String... argumentsArray) throws JspException, NoSuchMessageException
    {
        MessageSource messageSource = getMessageSource();
        if (messageSource == null)
        {
            throw new JspTagException("No corresponding MessageSource found");
        }

        String resolvedCode = ExpressionEvaluationUtils.evaluateString("code", messageCode, pageContext);

        // We have no fallback text to consider.
        return messageSource.getMessage(resolvedCode, argumentsArray, getRequestContext().getLocale());
    }

    private MessageSource getMessageSource()
    {
        return getRequestContext().getMessageSource();
    }

    private final RequestContext getRequestContext()
    {
        return this.requestContext;
    }
}

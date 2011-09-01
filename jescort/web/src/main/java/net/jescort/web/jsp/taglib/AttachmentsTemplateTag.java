package net.jescort.web.jsp.taglib;

import net.jescort.domain.forum.Attachment;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
public class AttachmentsTemplateTag extends TagSupport implements TryCatchFinally
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
        if (this.requestContext == null)
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
            sb.append("<br><span class=\"desc info\">").append(resolveMessage("message.number_of_downloads", null)).append(attachment.getDownloads()).append("</span>\n");
            sb.append("</li>\n");
        }
        sb.append("</ul>\n");
        sb.append("</div>");

        return sb.toString();
    }

    private String resolveMessage(String messageCode, String arguments) throws JspException, NoSuchMessageException
    {
		MessageSource messageSource = getMessageSource();
		if (messageSource == null)
        {
			throw new JspTagException("No corresponding MessageSource found");
		}

		String resolvedCode = ExpressionEvaluationUtils.evaluateString("code", messageCode, pageContext);

		// We have a code or default text that we need to resolve.
		Object[] argumentsArray = resolveArguments(arguments);

		// We have no fallback text to consider.
		return messageSource.getMessage(resolvedCode, argumentsArray, getRequestContext().getLocale());
	}

    private Object[] resolveArguments(Object arguments) throws JspException
    {
		if (arguments instanceof String)
        {
			String[] stringArray = StringUtils.delimitedListToStringArray((String) arguments, ",");
			if (stringArray.length == 1)
            {
				Object argument = ExpressionEvaluationUtils.evaluate("argument", stringArray[0], pageContext);
				if (argument != null && argument.getClass().isArray())
                {
					return ObjectUtils.toObjectArray(argument);
				}
				else
                {
					return new Object[] {argument};
				}
			}
			else
            {
				Object[] argumentsArray = new Object[stringArray.length];
				for (int i = 0; i < stringArray.length; i++)
                {
					argumentsArray[i] = ExpressionEvaluationUtils.evaluate("argument[" + i + "]", stringArray[i], pageContext);
				}
				return argumentsArray;
			}
		}
		else if (arguments instanceof Object[])
        {
			return (Object[]) arguments;
		}
		else if (arguments instanceof Collection)
        {
			return ((Collection) arguments).toArray();
		}
		else if (arguments != null)
        {
			// Assume a single argument object.
			return new Object[] {arguments};
		}
		else
        {
			return null;
		}
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

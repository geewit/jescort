package net.jescort.web.jsp.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.ExpressionEvaluationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-30
 * Time: 上午11:02
 */
public class PagerTemplateTag extends TagSupport implements TryCatchFinally
{
    //private transient final Log logger = LogFactory.getLog(PagerTemplateTag.class);

    private Page<?> page;
    private String requestUrl;

    private static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE = "org.springframework.web.servlet.tags.REQUEST_CONTEXT";
    private static final ThreadLocal<ConcurrentHashMap<Page<?>, String>> PAGER_HTML_THREADLOCAL = new ThreadLocal<ConcurrentHashMap<Page<?>, String>>();

    private RequestContext requestContext;

    public void setPage(Page<?> page)
    {
        this.page = page;
    }

    public void setRequestUrl(String requestUrl)
    {
        this.requestUrl = requestUrl;
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

        try{
            pageContext.getOut().write(getPagerHtml());
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

    private String getPagerHtml() throws JspException
    {
        ConcurrentHashMap<Page<?>, String> threadLocalMap = PAGER_HTML_THREADLOCAL.get();
        String html = null;
        if(threadLocalMap != null)
        {
            html = threadLocalMap.get(page);
        }
        else
        {
            threadLocalMap = new ConcurrentHashMap<Page<?>, String>();
        }
        if(org.apache.commons.lang.StringUtils.isBlank(html))
        {
            html = writeHtml();
            threadLocalMap.put(page, html);
            PAGER_HTML_THREADLOCAL.set(threadLocalMap);
        }
        return html;
    }

    private String writeHtml() throws JspException
    {
        StringBuffer sb = new StringBuffer();
        final int number = page.getNumber() + 1;
        int totalPages = page.getTotalPages();
        if(totalPages == 0)
        {
            totalPages = 1;
        }
        String contextPath = requestContext.getContextPath();
        String requstUri = contextPath + this.requestUrl;
        sb.append("<ul class=\"pagination left");
        if(totalPages < 2)
        {
            sb.append(" no_pages");
        }
        sb.append("\">\n" + "<li class=\"pagejump clickable\">");
        sb.append(totalPages);
        if(totalPages > 1)
        {
            sb.append(resolveMessage("message.pages", null));
        }
        else
        {
            sb.append(resolveMessage("message.page", null));
        }
        sb.append("<img src=\"" + contextPath + "/static/images/dropdown.png\" alt=\"+\"/>");
        sb.append("</li>\n");

        boolean isFirstPage = page.isFirstPage();
        if(!isFirstPage)
        {
            if(page.hasPreviousPage())
            {
                int previousPage = number - 1;
                sb.append("<li><a href=\"" + requstUri + "/page/1\" title=\"Next page\" rel=\"first\">");
                sb.append(resolveMessage("message.first_page", null));
                sb.append("</a></li>\n");
                sb.append("<li><a href=\"" + requstUri + "/page/" + previousPage + "\" title=\"Next page\" rel=\"previous\">");
                sb.append(resolveMessage("message.previous_page", null));
                sb.append("</a></li>\n");
            }

            int lastPages = number - 1;
            lastPages = lastPages > 3 ? 3 : lastPages;
            int i = number - lastPages;

            while (i < number)
            {
                sb.append("<li><a href=\"" + requstUri + "/page/" + i + "\" title=\"" + i + "\">" + i + "</a></li>\n");
                i++;
            }
        }
        sb.append("<li class=\"active\">" + number + "</li>\n");
        if(!page.isLastPage())
        {
            int i = number;
            int restPages = totalPages - number;
            restPages = (restPages > 3 ? 3 : restPages) + number;

            while (i < restPages)
            {
                i++;
                sb.append("<li><a href=\"" + requstUri + "/page/" + i + "\" title=\"" + i + "\">" + i + "</a></li>\n");
            }

            if(page.hasNextPage())
            {
                int nextPage = number + 1;
                sb.append("<li><a href=\"" + requstUri + "/page/" + nextPage + "\" title=\"Next page\" rel=\"next\">");
                sb.append(resolveMessage("message.next_page", null));
                sb.append("</a></li>\n");
                sb.append("<li><a href=\"" + requstUri + "/page/" + totalPages + "\" title=\"Last page\" rel=\"last\">");
                sb.append(resolveMessage("message.last_page", null));
                sb.append("</a></li>\n");
            }
        }

        sb.append("</ul>");
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

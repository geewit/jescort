package net.jescort.web.jsp.taglib;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.ExpressionEvaluationUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-30
 * Time: 上午11:02
 */
public class PagerTemplateTag extends TagSupport implements TryCatchFinally
{
   private transient final Log logger = LogFactory.getLog(PagerTemplateTag.class);

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

        try
        {
            pageContext.getOut().write(getPagerHtml());
            return EVAL_BODY_INCLUDE;
        } catch (IOException ioe)
        {
            throw new JspException(ioe);
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
        if (threadLocalMap != null)
        {
            html = threadLocalMap.get(page);
        } else
        {
            threadLocalMap = new ConcurrentHashMap<Page<?>, String>();
        }
        if (org.apache.commons.lang.StringUtils.isBlank(html))
        {
            html = writeHtml();
            threadLocalMap.put(page, html);
            PAGER_HTML_THREADLOCAL.set(threadLocalMap);
        }
        return html;
    }

    private String writeHtml() throws JspException
    {
        int totalPages = page.getTotalPages();
        logger.debug("totalPages == " + totalPages);
        if (totalPages <= 1)
        {
            return StringUtils.EMPTY;
        }
        StringBuffer sb = new StringBuffer();
        final int previousPage = page.getNumber();
        final int currentPage = previousPage + 1;
        final int nextPage = currentPage + 1;
        String contextPath = requestContext.getContextPath();
        String requstUri = contextPath + this.requestUrl;
        sb.append("<div class=\"pagination clearfix left\">\n");
        if (page.hasPreviousPage())
        {
            sb.append("    <ul class=\"ipsList_inline back left\">\n");
            if (!page.isFirstPage() && currentPage > 2)
            {
                sb.append("        <li class=\"first\"><a href=\"" + requstUri + "/page/1\" title=\"" + resolveMessage("message.first_page", StringUtils.EMPTY) + "\" rel=\"start\">");
                sb.append(resolveMessage("message.first_page", StringUtils.EMPTY));
                sb.append("</a></li>\n");
            }
            sb.append("        <li class=\"prev\"><a href=\"" + requstUri + "/page/" + previousPage + "\" title=\"" + resolveMessage("message.previous_page", StringUtils.EMPTY) + "\" rel=\"prev\">");
            sb.append(resolveMessage("message.previous_page", StringUtils.EMPTY));
            sb.append("</a></li>\n");
            sb.append("    </ul>\n");
        }
        sb.append("    <ul class=\"ipsList_inline left pages\">\n");
        sb.append("        <li class=\"pagejump clickable\"><a href=\"#\">\n");
        sb.append(resolveMessage("message.page_of_totalpages", String.valueOf(currentPage), String.valueOf(totalPages + 1)));
        sb.append("</a></li>\n");
        if (!page.isFirstPage())
        {
            int lastPages = previousPage > 3 ? 3 : previousPage;
            int i = currentPage - lastPages;

            while (i < currentPage)
            {
                sb.append("        <li class=\"page\"><a href=\"" + requstUri + "/page/" + i + "\" title=\"" + i + "\">" + i + "</a></li>\n");
                i++;
            }
        }
        sb.append("        <li class=\"page active\">" + currentPage + "</li>\n");
        if (!page.isLastPage())
        {
            int restPages = totalPages - currentPage;
            restPages = (restPages > 3 ? 3 : restPages) + currentPage;
            int i = currentPage;
            while (i < restPages)
            {
                i++;
                sb.append("        <li class=\"page\"><a href=\"" + requstUri + "/page/" + i + "\" title=\"" + i + "\">" + i + "</a></li>\n");
            }
        }
        sb.append("    </ul>\n");
        if (page.hasNextPage())
        {
            sb.append("    <ul class=\"ipsList_inline forward left\">\n");
            sb.append("        <li class=\"next\"><a href=\"" + requstUri + "/page/" + nextPage + "\" title=\"" + resolveMessage("message.next_page", StringUtils.EMPTY) + "\" rel=\"next\">");
            sb.append(resolveMessage("message.next_page", StringUtils.EMPTY));
            sb.append("</a></li>\n");
            sb.append("        <li class=\"last\"><a href=\"" + requstUri + "/page/" + totalPages + "\" title=\"" + resolveMessage("message.last_page", StringUtils.EMPTY) + "\" rel=\"last\">");
            sb.append(resolveMessage("message.last_page", StringUtils.EMPTY));
            sb.append("</a></li>\n");
            sb.append("    </ul>\n");
        }
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

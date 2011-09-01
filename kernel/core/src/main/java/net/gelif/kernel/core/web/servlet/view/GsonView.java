package net.gelif.kernel.core.web.servlet.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GsonView extends WebApplicationObjectSupport implements View, BeanNameAware
{
    public final static String DEFAULT_CONTENT_TYPE = MediaType.APPLICATION_JSON.toString();
    
    public final static String GSON_ROOT = "gson";
    
    private final static String DEFAULT_ENCODING = "UTF-8";
    
    private String beanName;
    
    private Set<String> renderedAttributes;
    
    private String requestContextAttribute;
    
    private boolean disableCaching = true;
    
    public final static GsonView instance = new GsonView();
    
    public GsonView()
    {
    }
    
    public void setDisableCaching(boolean disableCaching)
    {
        this.disableCaching = disableCaching;
    }
    
    /**
     * Returns the attributes in the model that should be rendered by this view.
     */
    public Set<String> getRenderedAttributes()
    {
        return renderedAttributes;
    }
    
    /**
     * Sets the attributes in the model that should be rendered by this view.
     * When set, all other model attributes will be ignored.
     */
    public void setRenderedAttributes(Set<String> renderedAttributes)
    {
        this.renderedAttributes = renderedAttributes;
    }
    
    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setCharacterEncoding(DEFAULT_ENCODING);
        if(disableCaching)
        {
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
            response.addDateHeader("Expires", 1L);
        }
    }
    
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrintWriter writer = response.getWriter();
        Object object = model.get(GSON_ROOT);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(object);
        logger.debug("json = " + json);
        writer.print(json);
    }
    
    public void setBeanName(String beanName)
    {
        this.beanName = beanName;
    }
    
    @Override
    public String getContentType()
    {
        return MediaType.APPLICATION_JSON.toString(); //To change body of implemented methods use File | Settings | File Templates.
    }
    
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(logger.isTraceEnabled())
        {
            logger.trace("Rendering view with name '" + this.beanName + "' with model " + model);
        }
        
        // Consolidate static and dynamic model attributes.
        Map<String, Object> mergedModel = new HashMap<String, Object>(model != null ? model.size() : 0);
        if(model != null)
        {
            mergedModel.putAll(model);
        }
        
        // Expose RequestContext?
        if(this.requestContextAttribute != null)
        {
            mergedModel.put(this.requestContextAttribute, createRequestContext(request, response, mergedModel));
        }
        
        prepareResponse(request, response);
        renderMergedOutputModel(mergedModel, request, response);
    }
    
    protected RequestContext createRequestContext(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model)
    {
        return new RequestContext(request, response, getServletContext(), model);
    }
}

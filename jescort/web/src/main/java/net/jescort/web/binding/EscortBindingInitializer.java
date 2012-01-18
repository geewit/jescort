package net.jescort.web.binding;

import net.gelif.kernel.core.config.JescortConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author admin@gelif.net
 */

public class EscortBindingInitializer implements WebBindingInitializer
{
    private Validator validator;

    public final void setValidator(Validator validator)
    {
        this.validator = validator;
    }

    public final Validator getValidator()
    {
        return this.validator;
    }

    public void initBinder(WebDataBinder binder, WebRequest request)
    {
        binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
        binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
        binder.registerCustomEditor(Float.class, null, new CustomNumberEditor(Float.class, NumberFormat.getCurrencyInstance(), true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.setDisallowedFields(StringUtils.split(disallowedFields, ","));
        if (this.validator != null && binder.getTarget() != null && this.validator.supports(binder.getTarget().getClass()))
        {
            binder.setValidator(this.validator);
        }
    }

    private final static String disallowedFields = JescortConfig.getProperty("webbinding.disallowed.fields");
}

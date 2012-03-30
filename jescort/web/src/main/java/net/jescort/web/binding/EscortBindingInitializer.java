/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.web.binding;

import net.gelif.kernel.core.config.JescortConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author admin@gelif.net
 */

public class EscortBindingInitializer implements WebBindingInitializer
{
    private transient final static Log logger = LogFactory.getLog(EscortBindingInitializer.class);
    
    private Validator validator;

    private final static SimpleDateFormat dateFormat = initDateFormat();

    private final static SimpleDateFormat initDateFormat()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        return dateFormat;
    }
    
    private Date convertDate(String strDate)
    {
        try
        {
            return dateFormat.parse(strDate);
        } catch(ParseException e)
        {
            logger.debug(e.toString());
            return null;
        }
    }

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
        binder.registerCustomEditor(Calendar.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Calendar calendar = null;
                Date date = convertDate(text);
                if(date != null)
                {
                    calendar = new GregorianCalendar();
                    calendar.setTime(date);
                }
                setValue(calendar);
            }

            @Override
            public String getAsText()
            {
                return dateFormat.format(getValue());
            }
        });

        binder.registerCustomEditor(Calendar.class, new CustomDateEditor(dateFormat, false));
        binder.setDisallowedFields(StringUtils.split(disallowedFields, ","));
        if (this.validator != null && binder.getTarget() != null && this.validator.supports(binder.getTarget().getClass()))
        {
            binder.setValidator(this.validator);
        }
    }

    private final static String disallowedFields = JescortConfig.getProperty("webbinding.disallowed.fields");
}

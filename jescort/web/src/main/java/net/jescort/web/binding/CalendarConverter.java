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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-22
 * Time: 上午9:01
 */
public class CalendarConverter implements Converter<String, Calendar>
{
    private transient final static Log logger = LogFactory.getLog(CalendarConverter.class);
    
    private final static SimpleDateFormat dateFormat = initDateFormat();

    private final static SimpleDateFormat initDateFormat()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        return dateFormat;
    }

    @Override
    public Calendar convert(String source)
    {
        Calendar calendar = null;
        Date date = null;
        try
        {
            date = dateFormat.parse(source);
        } catch(ParseException e)
        {
            logger.debug(e.toString());
        }
        if(date != null)
        {
            calendar = new GregorianCalendar();
            calendar.setTime(date);
        }
        return calendar;
    }
}

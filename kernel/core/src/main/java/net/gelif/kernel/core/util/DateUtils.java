package net.gelif.kernel.core.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils extends org.apache.commons.lang.time.DateUtils
{
    private static Log logger = LogFactory.getLog(DateUtils.class);
    
    public static final String BUNDLE_KEY = "escort";
    
    private static String timePattern = "HH:mm";
    
    // ~ Methods
    // ================================================================
    
    /**
     * Return default datePattern (MM/dd/yyyy)
     * 
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern()
    {
        final Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try
        {
            defaultDatePattern = ResourceBundle.getBundle(BUNDLE_KEY, locale).getString("date.format");
        }
        catch(final MissingResourceException mse)
        {
            defaultDatePattern = "MM/dd/yyyy";
        }
        
        return defaultDatePattern;
    }
    
    public static String[] getDatePatterns()
    {
        final Locale locale = LocaleContextHolder.getLocale();
        String[] defaultDatePatternArray = {"yyyy-MM-dd"};
        try
        {
            defaultDatePatternArray = StringUtils.split(ResourceBundle.getBundle(BUNDLE_KEY, locale).getString("date.format"), ",");
            logger.info("defaultDatePatternArray == " + defaultDatePatternArray);
        }
        catch(Exception e)
        {
            logger.debug("Exception: '" + e.toString());
        }
        
        return defaultDatePatternArray;
    }
    
    public static String getDateTimePattern()
    {
        return getDatePattern() + " HH:mm:ss.S";
    }
    
    /**
     * This method attempts to convert an Oracle-formatted date in the form
     * dd-MMM-yyyy to mm/dd/yyyy.
     * 
     * @param date
     *            date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(final Date date)
    {
        SimpleDateFormat dateFormat;
        String returnValue = StringUtils.EMPTY;
        
        if(null != date)
        {
            dateFormat = new SimpleDateFormat(getDatePattern());
            returnValue = dateFormat.format(date);
        }
        
        return (returnValue);
    }
    
    /**
     * This method generates a string representation of a date/time in the
     * format you specify on input
     * 
     * @param mask
     *            the date pattern the string is in
     * @param strDate
     *            a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     */
    public static Date convertStringToDate(final String mask, final String strDate) throws ParseException
    {
        SimpleDateFormat dateFormat;
        Date date;
        dateFormat = new SimpleDateFormat(mask);
        
        if(logger.isInfoEnabled())
        {
            logger.info("converting '" + strDate + "' to date with mask '" + mask + "'");
        }
        
        try
        {
            date = dateFormat.parse(strDate);
        }
        catch(final ParseException pe)
        {
            logger.warn("ParseException: " + pe);
            throw new ParseException(pe.toString(), pe.getErrorOffset());
        }
        
        return (date);
    }
    
    /**
     * This method returns the current date time in the format: MM/dd/yyyy HH:MM
     * a
     * 
     * @param time
     *            the current time
     * @return the current date/time
     */
    public static String getTimeNow(final Date time)
    {
        return getDateTime(timePattern, time);
    }
    
    /**
     * This method returns the current date in the format: MM/dd/yyyy
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException
    {
        final Date today = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat(getDatePattern());
        
        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        final String todayAsString = dateFormat.format(today);
        final Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        
        return cal;
    }
    
    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     * 
     * @param mask
     *            the date pattern the string is in
     * @param date
     *            a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(final String mask, final Date date)
    {
        SimpleDateFormat dateFormat = null;
        String returnValue = StringUtils.EMPTY;
        
        if(null == date)
        {
            logger.error("date is null!");
        }
        else
        {
            dateFormat = new SimpleDateFormat(mask);
            returnValue = dateFormat.format(date);
        }
        
        return (returnValue);
    }
    
    /**
     * This method generates a string representation of a date based on the
     * System Property 'dateFormat' in the format you specify on input
     * 
     * @param aDate
     *            A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(final Date aDate)
    {
        return getDateTime(getDatePatterns()[0], aDate);
    }
    
    /**
     * This method converts a String to a date using the datePattern
     * 
     * @param strDate
     *            the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException
     */
    public static Date convertStringToDate(final String strDate) throws ParseException
    {
        Date date = null;
        
        try
        {
            String[] datePatterns = getDatePatterns();
            if(logger.isInfoEnabled())
            {
                logger.info("converting date with pattern: " + datePatterns);
            }
            date = parseDate(strDate, datePatterns);
        }
        catch(final ParseException pe)
        {
            logger.error("Could not convert '" + strDate + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.toString(), pe.getErrorOffset());
            
        }
        
        return date;
    }

    public static int getAge(Calendar birthday)
    {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        if (birthday.after(now))
        {
            throw new IllegalArgumentException("Can't be born in the future");
        }
        int day1 = now.get(Calendar.DAY_OF_YEAR);
        int day2 = birthday.get(Calendar.DAY_OF_YEAR);
        int age = birthday.get(Calendar.YEAR) - now.get(Calendar.YEAR);
        if (day2 < day1)
        {
            age--;
        }
        return age;
    }
}

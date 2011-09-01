package net.gelif.kernel.core.util;

import net.gelif.kernel.core.Timezone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-11
 * Time: 下午9:34
 */
public class TimeZoneUtils
{
    public final static List<Timezone> ALL_TIMEZONES = getAllAvailableTimezones();

    public final static List<Timezone> getAllAvailableTimezones()
    {
        String[] allTimeZones = TimeZone.getAvailableIDs();

        List<Timezone> timeZones = new ArrayList<Timezone>();
        Pattern pattern = Pattern.compile("^[A-Z][a-z_-]+/[A-Z][a-zA-Z_-]+$");

        TimeZone timeZone;
        for(String timeZoneId : allTimeZones)
        {
            if(pattern.matcher(timeZoneId).matches())
            {
                timeZone = TimeZone.getTimeZone(timeZoneId);
                timeZones.add(new Timezone(timeZoneId, String.valueOf(timeZone.getRawOffset()/3600000)));
            }
        }
        return timeZones;
    }
}

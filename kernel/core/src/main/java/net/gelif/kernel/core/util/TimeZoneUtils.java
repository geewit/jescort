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
package net.gelif.kernel.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;
import net.gelif.kernel.core.Timezone;

/**
 * Created by IntelliJ IDEA. User: admin@gelif.net Date: 11-8-11 Time: 下午9:34
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
                timeZones.add(new Timezone(timeZoneId, String.valueOf(timeZone.getRawOffset() / 3600000)));
            }
        }
        return timeZones;
    }
}

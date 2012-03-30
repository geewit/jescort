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

import org.apache.commons.lang.StringUtils;

import java.io.File;

public final class FilepathUtils
{
    public static String idToFilepath(Integer id)
    {
        return idToFilepath(id.toString());
    }

    public static String idToFilepath(String id)
    {
        return idToFilepath(id, 8);
    }

    public static String idToFilepath(String prefix, String id)
    {
        return idToFilepath(StringUtils.EMPTY, id, 8);
    }

    public static String idToFilepath(String id, int length)
    {
        return idToFilepath(StringUtils.EMPTY, id, length);
    }

    public static String idToFilepath(String prefix, String id, int length)
    {
        prefix = prefix.replace("/", File.separator);
        StringBuilder builder = new StringBuilder(prefix);
        builder.append(File.separator);
        for(int i = 0; i < length; i++)
        {
            builder.append(id.substring(i, i + 1)).append(File.separator);
        }
        return builder.toString();
    }

    public static String idTofullFilepath(String prefix, String suffix, String id)
    {
        return idTofullFilepath(prefix, suffix, id, 8);
    }

    public static String filenameTofullFilepath(String prefix, String filename)
    {
        int pos = filename.indexOf(".");
        if(pos > 0)
        {
            return idTofullFilepath(prefix, filename.substring(pos + 1), filename.substring(0, pos), 8);
        }
        else
        {
            return prefix + filename;
        }
    }
    
    public static String idTofullFilepath(String prefix, String suffix, String id, int length)
    {
        prefix = prefix.replace("/", File.separator);
        StringBuilder builder = new StringBuilder(prefix);
        builder.append(File.separator);
        for(int i = 0; i < length; i++)
        {
            builder.append(id.substring(i, i + 1)).append(File.separator);
        }
        builder.append(id).append(".").append(suffix);
        return builder.toString();
    }
    
    public static String convertSeparator(String uri)
    {
        return uri.replace("\\", "/");
    }
}

package net.gelif.kernel.core.util;

import java.io.File;

public final class FilenameUtils
{
    public static String idToFilepath(Integer id)
    {
        return idToFilepath(id.toString());
    }
    
    public static String idToFilepath(String id)
    {
        String path = File.separator;
        for(int i = 0; i < 8; i++)
        {
            path = path + id.substring(i, i + 1) + File.separator;
        }
        return path;
    }
}

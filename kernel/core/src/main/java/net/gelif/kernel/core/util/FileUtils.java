package net.gelif.kernel.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class FileUtils
{
    public static File getFile(byte[] bytes, String outputFile)
    {
        BufferedOutputStream stream = null;
        File file = null;
        try
        {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(bytes);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (stream != null)
            {
                try
                {
                    stream.close();
                } catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }
}

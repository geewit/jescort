package net.jescort.repository.impl;

import net.jescort.repository.FileRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-17
 * Time: 上午7:11
 */
public class FileRepositoryImpl implements FileRepository
{
    private transient final static Log logger = LogFactory.getLog(FileRepositoryImpl.class);

    private ResourceLoader resourceLoader;
    private String absolutePath;

    public void init() throws Exception
    {
        final Resource resource = resourceLoader.getResource("/");
        absolutePath = resource.getFile().getAbsolutePath();
        logger.debug("absolutePath == " + absolutePath);
        File file = new File(absolutePath);
        file.mkdirs();
    }

    @Override
    public String getAbsolutePath()
    {
        return absolutePath;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader)
    {
        this.resourceLoader = resourceLoader;
    }
}

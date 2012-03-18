package net.jescort.repository;

import org.springframework.context.ResourceLoaderAware;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-17
 * Time: 上午7:08
 */
public interface FileRepository extends ResourceLoaderAware
{
    public String getAbsolutePath();
}

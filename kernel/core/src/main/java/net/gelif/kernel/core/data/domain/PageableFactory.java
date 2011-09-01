package net.gelif.kernel.core.data.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-16
 * Time: 上午10:03
 */
public class PageableFactory
{
    public static Pageable create(Integer page, Integer size)
    {
        return new PageRequest(null != page && page > 1 ? page - 1 : 0, null != size && size > 0 ? size : 10);
    }
}

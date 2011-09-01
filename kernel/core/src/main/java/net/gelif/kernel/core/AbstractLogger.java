package net.gelif.kernel.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractLogger
{
    protected transient final Log logger = LogFactory.getLog(this.getClass());
}

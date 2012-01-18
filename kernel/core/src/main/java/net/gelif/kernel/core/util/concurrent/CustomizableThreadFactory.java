package net.gelif.kernel.core.util.concurrent;

import org.springframework.util.CustomizableThreadCreator;

import java.util.concurrent.ThreadFactory;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-11-21
 * Time: 下午9:25
 */
public class CustomizableThreadFactory extends CustomizableThreadCreator implements ThreadFactory
{

	/**
	 * Create a new CustomizableThreadFactory with default thread name prefix.
	 */
	public CustomizableThreadFactory() {
		super();
	}

	/**
	 * Create a new CustomizableThreadFactory with the given thread name prefix.
	 * @param threadNamePrefix the prefix to use for the names of newly created threads
	 */
	public CustomizableThreadFactory(String threadNamePrefix) {
		super(threadNamePrefix);
	}


	public Thread newThread(Runnable runnable) {
		return createThread(runnable);
    }
}

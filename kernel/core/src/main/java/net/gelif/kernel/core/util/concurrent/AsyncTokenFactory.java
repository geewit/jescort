package net.gelif.kernel.core.util.concurrent;

/**
 * @author badqiu
 * @see DefaultAsyncTokenFactory
 */
public interface AsyncTokenFactory<T>
{

    public AsyncToken<T> newToken();

}

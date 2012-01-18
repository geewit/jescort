package net.gelif.kernel.core.util.concurrent;

/**
 * AsyncToken的得到结果后的回调接口
 *
 * @author badqiu
 * @see AsyncToken
 */
public interface Responder<T>
{

    public void onResult(T result);

    public void onFault(Exception fault);

}

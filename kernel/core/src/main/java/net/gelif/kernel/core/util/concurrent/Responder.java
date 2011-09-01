package net.gelif.kernel.core.util.concurrent;

/**
 * AsyncToken的得到结果后的回调接口
 * 
 * @see AsyncToken
 * @author badqiu
 */
public interface Responder<T>
{
    
    public void onResult(T result);
    
    public void onFault(Exception fault);
    
}

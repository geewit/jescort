package net.gelif.kernel.core.util.concurrent;

/**
 * 用于捕获AsyncToken在通知Responder结果时,Responder的抛出的异常
 * 
 * @author badqiu
 */
public interface UncaughtExceptionHandler
{
    void uncaughtException(Responder<?> responder, Throwable e);
}
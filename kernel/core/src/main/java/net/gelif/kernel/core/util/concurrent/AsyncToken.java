package net.gelif.kernel.core.util.concurrent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 该类主要用于得到异步方法的执行结果. 使用示例如下.
 * <p/>
 * <pre>
 * public void testSendEmail()
 * {
 *     final String address = &quot;badqiu(a)gmail.com&quot;;
 *     final String subject = &quot;test&quot;;
 *     final String content = &quot;async token test&quot;;
 *
 *     //返回的token,包含token.addResponder()用于监听异步方法的执行结果
 *     AsyncToken token = sendAsyncEmail(address, subject, content);
 *
 *     //token可以继续传递给外部,以便外面感兴趣的listener监听这个异步方法的执行结果
 *     token.addResponder(new IResponder(){
 *         public void onFault(Exception fault)
 *         {
 *             System.out.println(&quot;email send fail,cause:&quot; + fault);
 *             //此处可以直接引用address,subject,content,如,我们可以再次发送一次
 *             sendAsyncEmail(address, subject, content);
 *         }
 *         public void onResult(Object result)
 *         {
 *             System.out.println(&quot;email send success,result:&quot; + result);
 *         }
 *     });
 * }
 *
 * public AsyncToken sendAsyncEmail(String address, String subject, String content)
 * {
 *     final AsyncToken token = new AsyncToken();
 *
 *     Topic topic = new Topic(new Runnable(){
 *         public void run()
 *         {
 *             try
 *             {
 *                 //do send email job...
 *                 token.setComplete(executeResult); //通知Responder token执行完
 *             }
 *             catch(Exception e)
 *             {
 *                 token.setFault(e); //通知Responder token发生错误
 *             }
 *         }
 *     });
 *     topic.start();
 *
 *     return token;
 * }
 *
 * </pre>
 * <p/>
 * 生成token请查看AsyncTokenTemplate
 *
 * @author badqiu
 * @see AsyncTokenTemplate
 */
public class AsyncToken<T>
{
    public static final String DEFAULT_TOKEN_GROUP = "default";

    //tokenGroup tokenName tokenDescription tokenId  用于可以增加描述信息
    private String tokenGroup = DEFAULT_TOKEN_GROUP;
    private String tokenName;
    private long tokenId;

    private List<Responder<T>> responders = new ArrayList<Responder<T>>(2);

    private UncaughtExceptionHandler uncaughtExceptionHandler;
    private T result;
    private Exception fault;
    private boolean isFiredResult;

    private CountDownLatch awaitResultSignal = null;

    private static AtomicLong tokenIdSequence = new AtomicLong(1);

    public AsyncToken()
    {
        this(null);
    }

    public AsyncToken(UncaughtExceptionHandler uncaughtExceptionHandler)
    {
        this(DEFAULT_TOKEN_GROUP, null);
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public AsyncToken(String tokenGroup, String tokenName)
    {
        setTokenGroup(tokenGroup);
        setTokenName(tokenName);
        this.tokenId = tokenIdSequence.getAndIncrement();
    }

    public String getTokenGroup()
    {
        return tokenGroup;
    }

    public void setTokenGroup(String tokenGroup)
    {
        if (tokenGroup == null)
        {
            throw new IllegalArgumentException("'tokenGroup' must be not null");
        }
        this.tokenGroup = tokenGroup;
    }

    public String getTokenName()
    {
        return tokenName;
    }

    public void setTokenName(String tokenName)
    {
        this.tokenName = tokenName;
    }

    public long getTokenId()
    {
        return tokenId;
    }

    /**
     * 增加监听器 addResponder(responder,false);
     *
     * @param responder
     */
    public void addResponder(final Responder<T> responder)
    {
        addResponder(responder, false);
    }

    /**
     * 增加监听器,如果AsyncToken已经拥有token的执行结果,
     * 则token会根据invokeResponderInOtherTopic参数决定是否在异步线程调用responder
     *
     * @param responder                   监听器
     * @param invokeResponderInOtherTopic true则另起线程调用responder
     */
    public void addResponder(final Responder<T> responder, boolean invokeResponderInOtherTopic)
    {
        responders.add(responder);

        if (isFiredResult)
        {
            if (invokeResponderInOtherTopic)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        fireResult2Responder(responder);
                    }
                });
            } else
            {
                fireResult2Responder(responder);
            }
        }
    }

    public List<Responder<T>> getResponders()
    {
        return responders;
    }

    public boolean hasResponder()
    {
        return responders != null && responders.size() > 0;
    }

    public UncaughtExceptionHandler getUncaughtExceptionHandler()
    {
        return uncaughtExceptionHandler;
    }

    public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler)
    {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    private void fireResult2Responder(Responder<T> responder)
    {
        try
        {
            if (fault != null)
            {
                responder.onFault(fault);
            } else
            {
                responder.onResult(result);
            }
        } catch (RuntimeException e)
        {
            if (getUncaughtExceptionHandler() != null)
            {
                getUncaughtExceptionHandler().uncaughtException(responder, e);
            } else
            {
                throw e;
            }
        } catch (Error e)
        {
            if (getUncaughtExceptionHandler() != null)
            {
                getUncaughtExceptionHandler().uncaughtException(responder, e);
            } else
            {
                throw e;
            }
        }
    }

    private void fireResult2Responders()
    {
        synchronized (this)
        {
            isFiredResult = true;
            if (awaitResultSignal != null)
            {
                awaitResultSignal.countDown();
            }
        }

        for(Responder<T> responder : responders)
        {
            fireResult2Responder(responder);
        }
    }

    public void setComplete()
    {
        setComplete(null);
    }

    public void setComplete(T result)
    {
        if (isFiredResult)
        {
            throw new IllegalStateException("token already fired");
        }
        this.result = result;
        fireResult2Responders();
    }

    public void setFault(Exception fault)
    {
        if (fault == null)
        {
            throw new NullPointerException();
        }
        if (isFiredResult)
        {
            throw new IllegalStateException("token already fired");
        }
        this.fault = fault;
        fireResult2Responders();
    }

    public boolean isDone()
    {
        synchronized (this)
        {
            return isFiredResult;
        }
    }

    /**
     * 等待得到token结果,测试一般使用此方法,因为jdk有相同功能的Future.findOne()可以使用
     *
     * @see Future
     */
    public Object waitForResult() throws InterruptedException, Exception
    {
        return waitForResult(false, -1, null);
    }

    /**
     * 等待得到token结果,测试一般使用此方法,因为jdk有相同功能的Future.findOne()可以使用
     *
     * @see Future
     */
    public Object waitForResult(long timeout, TimeUnit timeUnit) throws InterruptedException, Exception
    {
        return waitForResult(true, timeout, timeUnit);
    }

    private Object waitForResult(boolean hasTimeout, long timeout, TimeUnit timeUnit) throws InterruptedException, Exception
    {
        synchronized (this)
        {
            if (isFiredResult)
            {
                if (fault != null)
                {
                    throw fault;
                } else
                {
                    return result;
                }
            }

            awaitResultSignal = new CountDownLatch(1);
        }

        if (hasTimeout)
        {
            awaitResultSignal.await(timeout, timeUnit);
        } else
        {
            awaitResultSignal.await();
        }

        if (fault != null)
        {
            throw fault;
        } else
        {
            return result;
        }

    }

}

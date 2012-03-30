/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.gelif.modules.sendmail;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.internet.MimeMessage;

import net.gelif.kernel.core.util.concurrent.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.Assert;

/**
 * 使用线程池异步发送邮件的javaMailSender 每一个发送方法返回AsyncToken用于监听邮件是否发送成功
 *
 * @author admin@gelif.net
 * @see AsyncToken
 */
public class AsyncJavaMailSender implements InitializingBean, DisposableBean, BeanNameAware
{
    protected static final Log log = LogFactory.getLog(AsyncJavaMailSender.class);

    protected int sendMailThreadPoolSize = 0;
    protected ExecutorService executorService; //邮件发送的线程池
    protected JavaMailSender javaMailSender;
    protected boolean shutdownExecutorService = true;
    protected boolean waitForTasksToCompleteOnShutdown = true;
    protected AsyncTokenFactory<MimeMessage> asyncTokenFactory = new DefaultAsyncTokenFactory<MimeMessage>();

    private String beanName;

    public void afterPropertiesSet() throws Exception
    {
        if (executorService == null && sendMailThreadPoolSize > 0)
        {
            executorService = Executors.newFixedThreadPool(sendMailThreadPoolSize, new CustomizableThreadFactory(getClass().getSimpleName() + "-"));
            log.info("create send mail executorService,sendMailThreadPoolSize:" + sendMailThreadPoolSize);
        }

        Assert.notNull(javaMailSender, "javaMailSender must be not null");
        Assert.notNull(executorService, "executorService must be not null");
        Assert.notNull(asyncTokenFactory, "asyncTokenFactory must be not null");
    }

    public void destroy() throws Exception
    {
        if (shutdownExecutorService)
        {
            shutdown();
        }
    }

    public void shutdown()
    {
        log.info("Shutting down ExecutorService" + (this.beanName != null ? " '" + this.beanName + "'" : ""));

        if (waitForTasksToCompleteOnShutdown)
        {
            executorService.shutdown();
        } else
        {
            executorService.shutdownNow();
        }
    }

    public void setBeanName(String name)
    {
        this.beanName = name;
    }

    public boolean isWaitForTasksToCompleteOnShutdown()
    {
        return waitForTasksToCompleteOnShutdown;
    }

    public void setWaitForTasksToCompleteOnShutdown(boolean waitForTasksToCompleteOnShutdown)
    {
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
    }

    public void setSendMailThreadPoolSize(int sendMailThreadPoolSize)
    {
        this.sendMailThreadPoolSize = sendMailThreadPoolSize;
    }

    public ExecutorService getExecutorService()
    {
        return executorService;
    }

    public AsyncTokenFactory<MimeMessage> getAsyncTokenFactory()
    {
        return asyncTokenFactory;
    }

    public void setAsyncTokenFactory(AsyncTokenFactory<MimeMessage> asyncTokenFactory)
    {
        this.asyncTokenFactory = asyncTokenFactory;
    }

    public void setExecutorService(ExecutorService executorService)
    {
        this.executorService = executorService;
    }

    public JavaMailSender getJavaMailSender()
    {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    public void setShutdownExecutorService(boolean shutdownExecutorService)
    {
        this.shutdownExecutorService = shutdownExecutorService;
    }

    public boolean isShutdownExecutorService()
    {
        return shutdownExecutorService;
    }

    public MimeMessage createMimeMessage()
    {
        return javaMailSender.createMimeMessage();
    }

    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException
    {
        return javaMailSender.createMimeMessage(contentStream);
    }

    public AsyncToken<MimeMessage> send(final MimeMessage mimeMessage) throws MailException
    {
        return AsyncTokenUtils.execute(executorService, asyncTokenFactory, new Runnable()
        {
            public void run()
            {
                javaMailSender.send(mimeMessage);
            }
        });
    }

    public AsyncToken<MimeMessage> send(final MimeMessage[] mimeMessages) throws MailException
    {
        return AsyncTokenUtils.execute(executorService, asyncTokenFactory, new Runnable()
        {
            public void run()
            {
                javaMailSender.send(mimeMessages);
            }
        });
    }

    public AsyncToken<MimeMessage> send(final MimeMessagePreparator mimeMessagePreparator) throws MailException
    {
        return AsyncTokenUtils.execute(executorService, asyncTokenFactory, new Runnable()
        {
            public void run()
            {
                javaMailSender.send(mimeMessagePreparator);
            }
        });
    }

    public AsyncToken<MimeMessage> send(final MimeMessagePreparator[] mimeMessagePreparators) throws MailException
    {
        return AsyncTokenUtils.execute(executorService, asyncTokenFactory, new Runnable()
        {
            public void run()
            {
                javaMailSender.send(mimeMessagePreparators);
            }
        });
    }

    public AsyncToken<MimeMessage> send(final SimpleMailMessage simpleMessage) throws MailException
    {
        return AsyncTokenUtils.execute(executorService, asyncTokenFactory, new Runnable()
        {
            public void run()
            {
                javaMailSender.send(simpleMessage);
            }
        });
    }

    public AsyncToken<MimeMessage> send(final SimpleMailMessage[] simpleMessages) throws MailException
    {
        return AsyncTokenUtils.execute(executorService, asyncTokenFactory, new Runnable()
        {
            public void run()
            {
                javaMailSender.send(simpleMessages);
            }
        });
    }

}

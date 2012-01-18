package net.gelif.kernel.core.util.concurrent;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class DefaultAsyncTokenFactory<T> implements AsyncTokenFactory<T>
{

    private String tokenGroup = AsyncToken.DEFAULT_TOKEN_GROUP;
    private String tokenName;
    private List<Responder<T>> responders = new ArrayList<Responder<T>>();
    private UncaughtExceptionHandler uncaughtExceptionHandler;

    public String getTokenGroup()
    {
        return tokenGroup;
    }

    public void setTokenGroup(String tokenGroup)
    {
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

    public List<Responder<T>> getResponders()
    {
        return responders;
    }

    public void setResponders(List<Responder<T>> responders)
    {
        Assert.notNull(responders, "responders must be not null");
        this.responders = responders;
    }

    public void addResponder(Responder<T> r)
    {
        this.responders.add(r);
    }

    public UncaughtExceptionHandler getUncaughtExceptionHandler()
    {
        return uncaughtExceptionHandler;
    }

    public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler)
    {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public AsyncToken<T> newToken()
    {
        AsyncToken<T> asyncToken = new AsyncToken<T>();

        asyncToken.setTokenGroup(tokenGroup);
        asyncToken.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        asyncToken.setTokenName(tokenName);

        for(Responder<T> responder : responders)
        {
            asyncToken.addResponder(responder);
        }

        return asyncToken;
    }

}

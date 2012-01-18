package net.gelif.kernel.core.exception;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-25
 * Time: 下午3:20
 */
public class SearchException extends RuntimeException
{
    public SearchException() {
        super();
    }


    /**
     * Construct WebloggerException with message string.
     *
     * @param s Error message string.
     */
    public SearchException(String s) {
        super(s);
    }


    /**
     * Construct WebloggerException, wrapping existing throwable.
     *
     * @param s Error message
     * @param t Existing connection to wrap.
     */
    public SearchException(String s, Throwable t) {
        super(s, t);
    }


    /**
     * Construct WebloggerException, wrapping existing throwable.
     *
     * @param t Existing exception to be wrapped.
     */
    public SearchException(Throwable t) {
        super(t);
    }
}

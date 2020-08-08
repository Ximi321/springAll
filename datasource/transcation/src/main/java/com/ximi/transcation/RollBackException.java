package com.ximi.transcation;

/**
 * 回滚的异常
 *
 * @author Ximi
 * @since 2020/08/08
 */
public class RollBackException extends Exception {

    public RollBackException() {
    }

    public RollBackException(String message) {
        super(message);
    }

    public RollBackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollBackException(Throwable cause) {
        super(cause);
    }

    public RollBackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

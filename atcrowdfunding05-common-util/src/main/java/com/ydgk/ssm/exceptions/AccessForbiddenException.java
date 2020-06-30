package com.ydgk.ssm.exceptions;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-30 15:31
 */
public class AccessForbiddenException extends RuntimeException {

    static final long serialVersionUID = -730312334897194576L;

    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

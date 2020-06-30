package com.ydgk.ssm.exceptions;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-30 10:28
 */
public class LoginFailedException extends RuntimeException {

    static final long serialVersionUID = -70348971907341415L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

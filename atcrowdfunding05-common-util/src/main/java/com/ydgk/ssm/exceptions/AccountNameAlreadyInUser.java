package com.ydgk.ssm.exceptions;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-02 14:54
 */
public class AccountNameAlreadyInUser extends RuntimeException {
    static final long serialVersionUID = -7034897190745766342L;

    public AccountNameAlreadyInUser() {
    }

    public AccountNameAlreadyInUser(String message) {
        super(message);
    }

    public AccountNameAlreadyInUser(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNameAlreadyInUser(Throwable cause) {
        super(cause);
    }

    public AccountNameAlreadyInUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

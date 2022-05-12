package com.lcl.pname.controllerconfig.security;

import com.lcl.pname.controllerconfig.ApplicationException;
import com.lcl.pname.responsestatus.ResultCode;
import org.springframework.security.core.AuthenticationException;

/**
 * @author lcl
 */
/*public class CaptchaException extends ApplicationException {
    public CaptchaException() {
    }

    public CaptchaException(String message) {
        super(ResultCode.CODE_ERROR, message);
    }

    public CaptchaException(String message, Throwable cause) {
        super(ResultCode.CODE_ERROR, message, cause);
    }

    public CaptchaException(Throwable cause) {
        super(ResultCode.CODE_ERROR, cause);
    }

    protected CaptchaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ResultCode.CODE_ERROR, message, cause, enableSuppression, writableStackTrace);
    }
}*/

public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaException(String msg) {
        super(msg);
    }
}

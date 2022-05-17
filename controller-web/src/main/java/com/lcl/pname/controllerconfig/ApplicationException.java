package com.lcl.pname.controllerconfig;

import com.lcl.pname.responsestatus.ResultCode;

/**
 * 自定义总异常,Exception 需要向外抛,运行时异常不需要,Exception多层外抛处理不符合规范.
 * */
public class ApplicationException extends RuntimeException {

    private final ResultCode resultCode;

    public ApplicationException() {
        super();
        resultCode = ResultCode.UNKNOWN;
    }

    public ApplicationException(ResultCode resultcode, String message) {
        super(message);
        this.resultCode = resultcode;
    }

    public ApplicationException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public ApplicationException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    protected ApplicationException(ResultCode resultCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.resultCode = resultCode;
    }
}

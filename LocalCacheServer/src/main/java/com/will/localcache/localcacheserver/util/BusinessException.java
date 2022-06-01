package com.will.localcache.localcacheserver.util;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * @author willWang
 */
public class BusinessException extends RuntimeException implements Serializable {

    private static final long SERIAL_VERSIONU_ID = -6003868869041167435L;

    /**
     * 异常码
     */
    private int status ;

    /**
     * 异常消息
     */
    private String message;

    /**
     * 是否格式化消息
     */
    private boolean isFormat = false;

    /**
     * 消息中配置的参数
     */
    private Object[] arguments;



    public BusinessException() {
    }

    public BusinessException(int status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    public BusinessException(Integer code, String message, Throwable throwable, Object... arguments) {
        super(message, throwable);
        this.message = message;
        this.status = code;
        this.arguments = arguments;
    }
    /**
     * 无异常的Exception
     *
     * @param exceptionCode
     */
    public BusinessException(ExceptionResolvable exceptionCode) {
        this(exceptionCode.getCode(), exceptionCode.getMessage(), null, new Object[0]);
    }

    /**
     * 无异常的Exception,但消息中配有参数
     *
     * @param exceptionCode
     */
    public BusinessException(ExceptionResolvable exceptionCode, Object... arguments) {
        this(exceptionCode.getCode(), exceptionCode.getMessage(), null, arguments);
    }

    /**
     * 有异常的Exception
     *
     * @param exceptionCode
     * @param throwable
     */
    public BusinessException(ExceptionResolvable exceptionCode, Throwable throwable) {
        this(exceptionCode.getCode(), exceptionCode.getMessage(), throwable, new Object[0]);
    }


    /**
     * 有异常的Exception并且消息中配有参数
     *
     * @param exceptionCode
     * @param throwable
     */
    public BusinessException(ExceptionResolvable exceptionCode, Throwable throwable, Object... arguments) {
        this(exceptionCode.getCode(), exceptionCode.getMessage(), throwable, arguments);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * 重写该方法防止toString得到的消息为非国际化消息
     */
    @Override
    public String getLocalizedMessage() {
        return this.getMessage();
    }

    @Override
    public String getMessage() {
        if (null != this.message && null != this.arguments && this.arguments.length > 0 && !isFormat) {
            //替换占位符{0}
            message = MessageFormat.format(message, this.arguments);
            this.isFormat = true;
        }
        return message;
    }
}

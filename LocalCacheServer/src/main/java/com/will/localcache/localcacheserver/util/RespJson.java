package com.will.localcache.localcacheserver.util;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;

/**
 * willWang
 * @param <T>
 */
@Component
public class RespJson<T> implements Serializable {
    private Integer code;

    private String msg;

    private T data;

    private boolean success;

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespJson<T> success(T data) {
        RespJson<T> respJson = new RespJson<>();
        respJson.data = data;
        respJson.code = ResponseCodeEnum.SUCCESS.getCode();
        respJson.msg = ResponseCodeEnum.SUCCESS.getDetail();
        return respJson;
    }


    /**
     * 成功
     *
     * @return
     */
    public static <T> RespJson<T> success() {
        RespJson<T> respJson = new RespJson<>();
        respJson.code = ResponseCodeEnum.SUCCESS.getCode();
        respJson.msg = ResponseCodeEnum.SUCCESS.getDetail();
        return respJson;
    }

    /**
     * 成功
     *
     * @param data
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RespJson<T> success(T data, String msg) {
        RespJson<T> respJson = new RespJson<>();
        respJson.data = data;
        respJson.code = ResponseCodeEnum.SUCCESS.getCode();
        respJson.msg = msg;
        return respJson;
    }

    /**
     * 失败
     *
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */

    public static <T> RespJson<T> fail(T data, int code, String msg) {
        RespJson<T> respJson = new RespJson<>();
        respJson.data = data;
        respJson.code = code;
        respJson.msg = msg;
        return respJson;
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @return
     */
    public static <T> RespJson<T> fail(int code, String msg) {
        RespJson<T> respJson = new RespJson<>();
        respJson.code = code;
        respJson.msg = msg;
        return respJson;
    }

    /**
     * 失败
     *
     * @param msg
     * @return
     */
    public static <T> RespJson<T> fail(String msg) {
        RespJson<T> respJson = new RespJson<>();
        respJson.code = ResponseCodeEnum.SYSTEM_ERROR.getCode();
        respJson.msg = msg;
        return respJson;
    }

    /**
     * 创建失败返回对象
     *
     * @param exceptionResolvable 异常编码对象
     * @return 失败返回对象
     */
    public static <T> RespJson<T> fail(ExceptionResolvable exceptionResolvable) {
        RespJson<T> respJson = new RespJson<>();
        respJson.code = exceptionResolvable.getCode();
        respJson.msg = exceptionResolvable.getMessage();
        return respJson;
    }

    public static <T> RespJson<T> fail(Exception e, ExceptionResolvable exceptionResolvable) {
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return fail(businessException.getStatus(), businessException.getMessage());
        } else if (exceptionResolvable != null) {
            return fail(exceptionResolvable);
        }
        return fail(e.getMessage());
    }

    public static <T> RespJson<T> tip(String msg) {
        RespJson<T> respJson = new RespJson<>();
        respJson.code = ResponseCodeEnum.SYSTEM_TIPS.getCode();
        respJson.msg = msg;
        return respJson;
    }

    public boolean isSuccess() {
        return Arrays.asList(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SYSTEM_TIPS.getCode()).contains(this.code);
    }

}
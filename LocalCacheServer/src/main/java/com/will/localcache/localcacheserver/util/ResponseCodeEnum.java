package com.will.localcache.localcacheserver.util;

/**
 * 使用currenthashmap实现缓存服务
 * @author willWang
 * @since 2022/6/1
 */
public enum ResponseCodeEnum {
    SUCCESS(0, "操作成功"),
    SYSTEM_TIPS(1, "系统提示"),
    SYSTEM_ERROR(-1, "系统错误"),
    PARAM_ERROR(-2, "参数错误"),
    DATA_ERROR(-3, "数据错误，请联系管理员"),
    ;
    private int code;
    private String detail;

    ResponseCodeEnum(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}

package com.springcloud.ribbon.service.b.response;


/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS("200", "操作成功!"),
    FAILED("500", "操作失败!"),
    VALIDATE_FAILED("404", "找不到对应的操作!"),
    UNAUTHORIZED("401", "暂未登录或token已经过期!"),
    FORBIDDEN("403", "没有相关权限!");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

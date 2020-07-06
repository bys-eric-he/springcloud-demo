package com.springcloud.ribbon.service.b.response;

/**
 * 封装API的错误码
 */
public interface IErrorCode {
    String getCode();
    String getMessage();
}
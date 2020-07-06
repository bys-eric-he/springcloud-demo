package com.springcloud.ribbon.service.a.comspringcloudribbonservicea.controller;


import com.springcloud.ribbon.service.a.comspringcloudribbonservicea.response.Result;
import com.springcloud.ribbon.service.a.comspringcloudribbonservicea.response.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/service")
public class ServiceAController {
    @RequestMapping("/hello")
    public Result<Object> testA(HttpServletRequest servletRequest) {
        String name = servletRequest.getParameter("name");
        String token = servletRequest.getHeader("token");

        return Result.success()
                .data("Hello world Service -->AA.AA.AA.AA.AA!"
                        + "--------------------parameter[{name="
                        + name + "}], token[{value="
                        + token + "}]-----------------")
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage());
    }
}

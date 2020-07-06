package com.springcloud.ribbon.service.b.controller;


import com.springcloud.ribbon.service.b.response.Result;
import com.springcloud.ribbon.service.b.response.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/service")
public class ServiceBController {
    @RequestMapping("/hello")
    public Result<Object> testB(HttpServletRequest servletRequest) {
        String name = servletRequest.getParameter("name");
        String token = servletRequest.getHeader("token");

        return Result.success()
                .data("Hello world Service -->BB.BB.BB.BB.BB!"
                        + "--------------------parameter[{name="
                        + name + "}], token[{value="
                        + token + "}]-----------------")
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage());
    }
}

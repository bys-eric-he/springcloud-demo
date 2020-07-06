package com.springcloud.api.gateway.comspringcloudapigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping("")
    public ResponseEntity fallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("服务暂不可用！！！！");
    }

    @GetMapping("/first")
    public String firstServiceFallback() {
        return "服务已经挂了! 请在一段时间后再试。";
    }

    @GetMapping("/second")
    public String secondServiceFallback() {
        return "Second Server overloaded！请在一段时间后再试。";
    }
}
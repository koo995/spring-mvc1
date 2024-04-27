package com.example.servlet.web.frontcontroller.v1.controller;

import com.example.servlet.web.frontcontroller.v1.ControllerV1;

import java.util.Map;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}

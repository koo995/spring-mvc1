package com.example.servlet.web.frontcontroller.v1.controller;

import com.example.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.servlet.web.frontcontroller.v1.ModelView;

import java.util.Map;

public class MemberFormControllerV1 implements ControllerV1 {

    /**
     *
     */
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}

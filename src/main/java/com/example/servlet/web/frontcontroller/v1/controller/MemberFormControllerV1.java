package com.example.servlet.web.frontcontroller.v1.controller;

import com.example.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.servlet.web.frontcontroller.v1.MyView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberFormControllerV1 implements ControllerV1 {

    /**
     *
     */
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}

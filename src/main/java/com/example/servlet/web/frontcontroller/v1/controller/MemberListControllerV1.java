package com.example.servlet.web.frontcontroller.v1.controller;


import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import com.example.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.servlet.web.frontcontroller.v1.ModelView;

import java.util.List;
import java.util.Map;

public class MemberListControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);
        return modelView;
    }
}

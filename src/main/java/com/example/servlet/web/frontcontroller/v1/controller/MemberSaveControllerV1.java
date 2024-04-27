package com.example.servlet.web.frontcontroller.v1.controller;


import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import com.example.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.servlet.web.frontcontroller.v1.ModelView;

import java.util.Map;

public class MemberSaveControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 파라미터 정보에서 username 과 age 가 필요하지 request, response 는 필요없다.
     */
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //ModelView 에 데이터를 보관한다.
        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);
        return modelView;
    }
}

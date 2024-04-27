package com.example.servlet.web.frontcontroller.v1.controller;


import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import com.example.servlet.web.frontcontroller.v1.ControllerV1;

import java.util.List;
import java.util.Map;

public class MemberListControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return "members"; // 이건 view 에 해당하는 jsp 이름이 members 라서 위와 똑같다.
    }
}

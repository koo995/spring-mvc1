package com.example.servlet.web.springMVC.v2;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 애너테이션 기반의 컨트롤러는 인터페이스가 아니라 딱 고정되어있지 않다. 매우 유연하다.
     * ModelAndView 을 반환해도 되고 String 으로 반환해도 된다.
     */
    @RequestMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    /**
     * 여기서 오류가 나타난다.
     */
    @RequestMapping()
    public String list(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

    /**
     * 파라미터를 직접 받을 수 있다.
     * Model 도 받을 수 있다.
     */
    @RequestMapping("/save")
    public String save(@RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}

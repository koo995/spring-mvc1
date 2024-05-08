package com.example.servlet.web.springMVC.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 스프리이 자동으로 스프링 빈으로 등록한다.
 * 스프링 mvc 에서 애노테이션 기반 컨트롤러로 인식한다.
 * 전체흐름 1. RequestMappingHandlerMapping 빈 생성
 *        2. 빈 초기화하면서 intiHandlerMethod 호출
 *        3. 빈 팩토리에 등록되어 있는 빈들 중 @Controller @RequestMapping 을 가지고 있는 빈을 가져온다.
 *        4. 핸들러가 될 수 있는 모든 메서드를 추출한다. (detectHandlerMethods)
 *        5. 추출된 메서드를 registry 에 등록한다. (registerHandlerMethod)
 */
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form"); // 여기서도 앞에서 설정했던 뷰리졸버가 적용된다.
    }
}

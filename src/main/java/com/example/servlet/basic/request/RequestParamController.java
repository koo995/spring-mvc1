package com.example.servlet.basic.request;

import com.example.servlet.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * ResponseBody 을 사용하면 "ok" 를 바로 응답바디에 박아버린다. 뷰리졸버를 사용하지 않고.
     * RequestParam 으로 파라미터를 받으면 굉장히 편리하다.
     * request.getParameter 와 같은 역할이다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     * 변수명과 파라미터 명이 같으면 애너테이션 안에 값을 생략할 수 있다.
     * 너무 다 생략하면 직관적으로 알기에는 조금 어려워서 과할 수 있다. (주관적)
     * 그러나... v3 과 v4에서 제대로 처리가 되지 않는다.
     * 생략하지 않거나 Gradle 로 빌드를 한다. (intelliJ 로 빌드해서 문제 발생)
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 필수 파라미터 여부를 설정할 수 있다.
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true, value = "username") String username,
            @RequestParam(required = false, value = "age") Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 디폴트 값을 설정할 수 있다.
     * 디폴트를 설정하면... 굳이 required 을 사용할 필요가 없다.
     * 참고: 거기다가 빈문자열도 처리가 가능하다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest", value = "username") String username,
            @RequestParam(required = false, defaultValue = "-1", value = "age") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * Map 으로도 받을 수 있다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * 마치 마법처럼 HelloData 객체가 생성되고, 요청 파라미터의 값도 모두 들어가 있다.
     * 스프링 MVC 는 @ModelAttribute 가 있으면
     * 1. HelloData 객체를 생성
     * 2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티(getXxx, setXxx)를 찾는다 그리고 해당 프로퍼티의 setter 를 호출해서 파라미터의 값을 바인딩한다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략이 가능하다!
     * 어? 그런데 @RequestParam 도 생략이 가능한데...?
     * 스프링은 String, int, Integer 와 같은 단순타입이면 @RequestParam 이 생략된 것으로 판단
     * 나머지는 @ModelAttribute 가 생략된 것으로 판단(argument resolver 로 지정해둔 타입은 예외!)
     * HttpServletResponse 같은 것은 argument resolver 로 지정되어 있다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}

package com.example.servlet.web.springMVC.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 과거버전 컨트롤러.
 * 기존의 애너테이션 Controller 은 핸들러 매핑을 따로 처리해줘야한다.
 * web.servlet.mvc.Controller 을 써야한다.
 * 스프링 빈의 이름을 "/springmvc/old-controller" url 패턴으로 맞추면 이 녀석이 호출된다.
 * 어떻게 이 컨트롤러가 호출된 것이지?
 * 이 컨트롤러가 호출되려면 다음 2가지가 필요하다.
 * 1. HandlerMapping
 *     핸들러 매핑에서 이 컨트롤러를 찾을 수 있어야 한다.
 *     예) 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑 구현체가 필요하다.
 * 2. HandlerAdapter
 *     핸들러 매핑을 통해서 찾은 핸들러를 실행할 수 있는 핸들러 어댑터가 필요하다.
 *     예) 'Controller' 인터페이스를 실행할 수 있는 핸들러 어댑터를 찾고 실행해야 한다.
 * 스프링은 이미 필요한 이 2가지도 구현해두었다.
 * HandlerMappings 에는 0 순위 RouterFunctionMappings : reactive 에서 사용되는 것이군..
 *                     1 순위 RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping 에 사용
 *                     2 순위 WelcomePageHandlerMapping
 *                     3 순위 BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.
 *                     4 순위 welcomePageNotAcceptableHandlerMapping
 *                     5     SimpleUrlHandlerMapping
 * HandlerAdapters 에는 0 순위 RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping 에서 사용
 *                     1 순위 HandlerFunctionAdapter
 *                     2     HttpRequestHandlerAdapter : HttpRequestHandler 처리
 *                     3     SimpleControllerHandlerAdapter : Controller 인터페이스(과거에 사용) 처리
 */
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}

package com.example.servlet.web.frontcontroller.v1;

import com.example.servlet.web.frontcontroller.v1.adapter.ControllerV1HandlerAdapter;
import com.example.servlet.web.frontcontroller.v1.adapter.ControllerV3HandlerAdapter;
import com.example.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import com.example.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 프론트 컨트롤러가 되게 하는 것이 많아졌다.
 * 그러나 실제 구현한 controller 는 되게 편해졌다.
 */
@Slf4j
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV1 extends HttpServlet {

    /**
     * 기존이랑 차이는 controllerMap 에서는 컨트롤러가 정해져 있는데
     * handlerMappingMap 에서는 아무거나 다 들어갈 수 있다.
     */
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV1() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FrontControllerServletV1.service");

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);
        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        /**
         * 핸들러를 처리할 수 있는 어댑터를 반복문을 통해서 찾는다.
         */
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.support(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 을 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // v1 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV1());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV1());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV1());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV1HandlerAdapter());
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

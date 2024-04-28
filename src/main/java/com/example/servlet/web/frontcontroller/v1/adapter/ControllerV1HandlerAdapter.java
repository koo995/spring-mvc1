package com.example.servlet.web.frontcontroller.v1.adapter;

import com.example.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.servlet.web.frontcontroller.v1.ModelView;
import com.example.servlet.web.frontcontroller.v1.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV1HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV1);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV1 controller = (ControllerV1) handler;

        /**
         * v3 와는 다르게 paramMap 과 model 을 받는다.
         * controller.process 의 반환값이 String 타입의 viewName 인데 여기서 기존과 맞지않는 점이 발생했다.
         * 이제 어댑터가 하는 역할이 중요해진다. modelView 을 생성하고 반환해준다. 이렇게 형식에 맞추어 반환한다.
         */
        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

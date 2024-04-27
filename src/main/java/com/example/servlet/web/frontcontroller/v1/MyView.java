package com.example.servlet.web.frontcontroller.v1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {
    private final String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 렌더가 호출되면 model 에서 값을 다 꺼내서 request 에 모두 담아버린다.
         */
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    /**
     * 궁금했던점?
     * jsp 는 HttpServletRequest 에 setAttribute 로 넣어야 jsp 의 표현식들로 굉장히 편하게 꺼내쓸 수 있다.
     * 참고로 다른 뷰 템플릿은 다른 곳에 집어넣어야 할지도 모른다.
     */
    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
//        model.forEach((key, value) -> request.setAttribute(key, value));
        model.forEach(request::setAttribute);
    }
}

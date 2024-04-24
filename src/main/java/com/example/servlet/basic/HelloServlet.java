package com.example.servlet.basic;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    /**
     * protected 된 녀석을 오버라이딩 해준다.
     * HttpServletRequest 이 녀석들은 인터페이스다.
     * was 서버에도 여러가지가 있는데 톰캣이 HttpServletRequest 이 녀석을 구현했다.
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        /**
         * 쿼리파라미터를 파싱하는 방법
         */
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        /**
         * 응답을 내보내는 방법
         */
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}

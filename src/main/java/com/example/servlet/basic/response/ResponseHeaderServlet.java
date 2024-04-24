package com.example.servlet.basic.response;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 응답 코드를 넣을 수 있다.
         */
        response.setStatus(HttpServletResponse.SC_OK);

        //[response-headers]
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 이렇게 하면 캐시를 완전히 무효화 하겠다는 것.
//        response.setHeader("Pragma", "no-cache"); // 과거버전까지 캐시를 없애는 것.
//        response.setHeader("my-header", "hello"); // 내가 원하는 임의의 헤더를 만든다.

        //[Header 의 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);


        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        /**
         * 기존에는 response.setHeader("Content-Type", "text/plain;charset=utf-8"); 이렇게 했지만
         * 이제는 이렇게 쓴다.
         * content-length 는 꼭 있어야 하는데 이건 자동으로 생성된다.
         */
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        /**
         * 쿠키를 만들어서 넣어준다.
         * response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); 이렇게 쓰는 것보다
         * 이렇게 쓰는 것이 더 편하다.
         */
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}

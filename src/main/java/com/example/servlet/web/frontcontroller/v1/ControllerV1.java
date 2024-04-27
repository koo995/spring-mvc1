package com.example.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *
 */
public interface ControllerV1 {

    /**
     * 서블릿이랑 비슷한 모양이고... 이름만 다르다.
     * 각 컨트롤러들은 이 인터페이스를 구현하면 된다. 그럼 프론트컨트롤러가 이 인터페이스를 호출해서 구현과 관계없이 로직의 일관성을 가져갈 수 있다.
     * 반환을 v1에서는 void로 했는데, v2에서는 MyView로 반환한다.
     */
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

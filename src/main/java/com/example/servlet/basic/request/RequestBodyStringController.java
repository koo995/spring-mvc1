package com.example.servlet.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * 항상 스트림은 bytecode 이기에 bytecode 로 문자를 받을 때는 어떤 인코딩으로 해서 문자를 바꿀 거야라고 항상 지정해줘야 한다.
     * 안하면 os 나 Java 실행의 디폴트가 적용된다.
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    /**
     * request 와 response 대신에
     * inputStream(reader) OutputStream(writer) 을 직접 받을 수 있다.
     * (지금은 굳이 request response 가 필요한 경우가 아니니까?)
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * 스트림 바꾸고 이런게 너무 싫다?
     * 메시지 컨버터를 이용한 HttpEntity 을 제공한다.
     * HttpEntity 는 http 메시지를 스펙화 한 것으로 생각하면 된다?
     * header 와 body 정보를 직접 조회할 수 있다. 응답에도 적용가능하고 view 도 조회안한다.
     * 참고: http 요청 파라미터와는 전혀 상관없다!!
     * HttpEntity 을 상속받은 녀석들로 몇가지 기능을 더 제공한다.
     */
    @PostMapping("/request-body-string-v3")
    public ResponseEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.CREATED); // 첫번째 파라미터로 메시지 바디를 받을 수 있다.
    }
}

package com.example.servlet.basic.response;


import com.example.servlet.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    /**
     * 위에는 그냥 문자열을 처리하는 것이였고
     * 아래부터는 json 을 처리하는 경우다.
     * 기본 문자 처리는 StringJackson2HttpMessageConverter
     * 기본 객체 처리는 MappingJackson2HttpMessageConverter <- 지금부터는 이 녀석이 사용될 것이다.
     * 사실 굉장히 많은 HttpMessageConverter 가 등록되어 있다. accept 헤더와 서버의 컨트롤러 반환타입 조합으로 선택된다.(사실은 MediaType 포함 더 고려한 조합으로?)
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * @ResponseBody 을 사용하는 것으로 helloData 을 그대로 넘길 수 있다.
     * 어 ? 상태코드는 어떻게 바꿈? @ResponseStatus 이 제공된다.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }

}

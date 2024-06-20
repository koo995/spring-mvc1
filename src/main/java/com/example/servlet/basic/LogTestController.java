package com.example.servlet.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        /**
         * 이런식으로 더하기 연산을 사용하면 안된다.
         * name 에 "Spring" 이 들어가고 더하기 "연산" 이 일어나서 메모리도 사용하고 cpu 도 사용한다.
         * 그런데 trace 로그를 쓰지도 않는데 문자열 더하기가 발생하여 필요도 없는 연산이 사용된다.
         * {} 이 방식은 메서드에 파라미터만 넘기는 것이다. trace 상태면 파라미터가 넘어가지 않아서 "연산"이 일어나지 않는다.
         * 그리고 내부에 버퍼링 멀티스레드 등등의 기능으로 테스트해보면 수십배의 성능차이가 나도록 최적화가 되어있다.
         */
        log.trace(" trace my log="+ name);

        log.trace("trace log={}", name);

        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
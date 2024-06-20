package com.example.servlet.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    /**
     * 이번엔 String 을 반환한다.
     * view 을 찾는다. @ResponseBody 와 헷갈리면 안된다! 이 녀석은 view 로 안가고 문자가 그대로 메시지바디에 박혀서 나간다.
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    /**
     * 이건 별로 권장하지 않는다.
     * 컨트롤러의 매칭된 Url 을 참고해서 view 의 경로로 생각한다.
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}

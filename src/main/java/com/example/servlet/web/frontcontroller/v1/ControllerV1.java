package com.example.servlet.web.frontcontroller.v1;

import java.util.Map;


public interface ControllerV1 {
    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}

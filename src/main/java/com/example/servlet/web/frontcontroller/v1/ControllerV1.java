package com.example.servlet.web.frontcontroller.v1;

import java.util.Map;

/**
 *
 */
public interface ControllerV1 {
    ModelView process(Map<String, String> paramMap);
}

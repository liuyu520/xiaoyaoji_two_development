package com.kunlunsoft.web.controller;

import com.common.dict.Constant2;
import com.common.util.SystemHWUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
public class DemoController {
    @ResponseBody
    @RequestMapping(value = "/query/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonDemo2(Model model, HttpServletRequest request, HttpServletResponse response
    ) {
        return Constant2.RESPONSE_RIGHT_RESULT;
    }
}

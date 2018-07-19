package com.kunlunsoft.web.controller;

import com.common.bean.BaseResponseDto;
import com.common.util.SystemHWUtil;
import com.kunlunsoft.model2.Module;
import com.kunlunsoft.mybatis.mapper2.ModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/project/module", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
public class APIModuleController {
    @Autowired
    private ModuleMapper moduleMapper;

    @ResponseBody
    @RequestMapping(value = "/query/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonQuery2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(name = "id", required = false) String id) {
        System.out.println("id :" + id);
        Module module = this.moduleMapper.selectByPrimaryKey(id);
        return BaseResponseDto.jsonValue(model);
    }
}

package com.kunlunsoft.service;

import com.common.bean.exception.LogicBusinessException;
import com.common.util.BeanHWUtil;
import com.io.hw.json.HWJacksonUtils;
import com.kunlunsoft.dto.ResponseArgsDto;
import com.kunlunsoft.model2.Interface;
import com.kunlunsoft.model2.response.ResponseArgsItem;
import com.kunlunsoft.mybatis.mapper2.InterfaceMapper;
import com.string.widget.util.ValueWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * see Interface
 */
@Service
public class InterfaceDetailService {
    @Autowired
    private InterfaceMapper interfaceMapper;

    public ResponseArgsDto getResponseArgsItems(String id) {
        Interface anInterface = this.interfaceMapper.selectByPrimaryKey(id);
        String responseargs = anInterface.getResponseargs();
        ResponseArgsDto responseArgsDto = new ResponseArgsDto();
        responseArgsDto.setId(id);
        responseArgsDto.setResponseargItems((List<ResponseArgsItem>) HWJacksonUtils.deSerializeList(responseargs, ResponseArgsItem.class));
        return responseArgsDto;
    }

    public ResponseArgsDto getResponseArgsItemsByName(String name) {
        List<Interface> interfaces = this.interfaceMapper.findByname(name);
//        String responseargs = findInterface(interfaces);
//        return (List<ResponseArgsItem>) HWJacksonUtils.deSerializeList(responseargs, ResponseArgsItem.class);
        return buildResponseArgsDto(interfaces);
    }

    /***
     *
     * @param url
     * @param method : q请求方法
     * @return
     */
    public ResponseArgsDto getResponseArgsItemsByUrlAndMethod(String url, String method) {
        List<Interface> interfaces = null;
        if (ValueWidget.isNullOrEmpty(method)) {
            interfaces = this.interfaceMapper.findByUrl(url);
        } else {
            interfaces = this.interfaceMapper.findByUrlAndRequestmethod(url, method);
        }
        return buildResponseArgsDto(interfaces);
    }

    private ResponseArgsDto buildResponseArgsDto(List<Interface> interfaces) {
        Interface aInterface = findInterface(interfaces);
        String responseargs = aInterface.getResponseargs();
        ResponseArgsDto responseArgsDto = new ResponseArgsDto();
        BeanHWUtil.copyProperties(aInterface, responseArgsDto);
        responseArgsDto.setResponseargItems((List<ResponseArgsItem>) HWJacksonUtils.deSerializeList(responseargs, ResponseArgsItem.class));
        return responseArgsDto;
    }

    private Interface findInterface(List<Interface> interfaces) {
        if (ValueWidget.isNullOrEmpty(interfaces)) {
            LogicBusinessException.throwException("1001", "没有找到接口,name:");
        }
        if (interfaces.size() > 1) {
            LogicBusinessException.throwException("1001", "找到多个接口,name:");
        }
        Interface anInterface = interfaces.get(0);
        return anInterface;
    }
}

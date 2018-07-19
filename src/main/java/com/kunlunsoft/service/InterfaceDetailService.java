package com.kunlunsoft.service;

import com.common.bean.exception.LogicBusinessException;
import com.io.hw.json.HWJacksonUtils;
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

    public List<ResponseArgsItem> getResponseArgsItems(String id) {
        Interface anInterface = this.interfaceMapper.selectByPrimaryKey(id);
        String responseargs = anInterface.getResponseargs();
        return (List<ResponseArgsItem>) HWJacksonUtils.deSerializeList(responseargs, ResponseArgsItem.class);
    }

    public List<ResponseArgsItem> getResponseArgsItemsByName(String name) {
        List<Interface> interfaces = this.interfaceMapper.findByname(name);
        String responseargs = findInterface(interfaces);
        return (List<ResponseArgsItem>) HWJacksonUtils.deSerializeList(responseargs, ResponseArgsItem.class);
    }

    /***
     *
     * @param url
     * @param method : q请求方法
     * @return
     */
    public List<ResponseArgsItem> getResponseArgsItemsByUrlAndMethod(String url, String method) {
        List<Interface> interfaces = null;
        if (ValueWidget.isNullOrEmpty(method)) {
            interfaces = this.interfaceMapper.findByUrl(url);
        } else {
            interfaces = this.interfaceMapper.findByUrlAndRequestmethod(url, method);
        }
        String responseargs = findInterface(interfaces);
        return (List<ResponseArgsItem>) HWJacksonUtils.deSerializeList(responseargs, ResponseArgsItem.class);
    }

    private String findInterface(List<Interface> interfaces) {
        if (ValueWidget.isNullOrEmpty(interfaces)) {
            LogicBusinessException.throwException("1001", "没有找到接口,name:");
        }
        if (interfaces.size() > 1) {
            LogicBusinessException.throwException("1001", "找到多个接口,name:");
        }
        Interface anInterface = interfaces.get(0);
        return anInterface.getResponseargs();
    }
}

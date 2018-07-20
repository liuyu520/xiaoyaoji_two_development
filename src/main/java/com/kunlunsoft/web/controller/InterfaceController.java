package com.kunlunsoft.web.controller;

import com.common.bean.BaseResponseDto;
import com.common.util.SystemHWUtil;
import com.io.hw.json.HWJacksonUtils;
import com.kunlunsoft.dto.ColumnDescResponseDto;
import com.kunlunsoft.dto.ResponseArgsDto;
import com.kunlunsoft.dto.request.DescriptionDto;
import com.kunlunsoft.model2.response.ResponseArgsItem;
import com.kunlunsoft.mybatis.mapper2.InterfaceMapper;
import com.kunlunsoft.service.InterfaceDetailService;
import com.string.widget.util.ValueWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/***
 * 接口 详细信息
 */
@RestController
@RequestMapping(value = "/interface", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
public class InterfaceController {
    @Autowired
    private InterfaceMapper interfaceMapper;
    @Autowired
    private InterfaceDetailService interfaceDetailService;
    @ResponseBody
    @RequestMapping(value = "/query/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonQuery2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(name = "id", required = true) String id) {
        System.out.println("id :" + id);
        List<ResponseArgsItem> responseargItems = interfaceDetailService.getResponseArgsItems(id).getResponseargItems();
        ResponseArgsDto responseArgsDto = new ResponseArgsDto();
//        BeanHWUtil.copyProperties(anInterface,responseArgsDto);
//        responseArgsDto.setResponseargItems(responseargItems);
        return BaseResponseDto.put2("test", "测试中文").put("value", responseargItems).toJson();
    }


    @ResponseBody
    @RequestMapping(value = "/setting/desc/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonFillDescription2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(name = "descJson", required = false) String descJson
            , @RequestParam(name = "id", required = true) String id) {
        Map<String, DescriptionDto> descriptionDtoMap = HWJacksonUtils.deSerializeMap(descJson, DescriptionDto.class);
        Map<String, String> descSimpleMap = parse2SimpleMap(descriptionDtoMap, null);
        ResponseArgsDto responseArgsDto = interfaceDetailService.getResponseArgsItems(id);
        List<ResponseArgsItem> responseargItems = responseArgsDto.getResponseargItems();
        settingRespDesc2(descSimpleMap, responseargItems);
        return HWJacksonUtils.getJsonP(responseargItems);
    }

    @ResponseBody
    @RequestMapping(value = "/auto/setting/desc/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonAutoFillDescription2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(name = "entity", required = false) String entity
            , @RequestParam(name = "id", required = false) String id
            , @RequestParam(name = "name", required = false) String name) {
        String entityName = "nearbyHouse";
        if (ValueWidget.isNullOrEmpty(entity)) {
            entity = entityName;
        }
        List<ResponseArgsItem> responseargItems = null;
        ResponseArgsDto responseArgsDto = null;
        if (ValueWidget.isNullOrEmpty(id)) {
            responseArgsDto = interfaceDetailService.getResponseArgsItemsByName(name);
        } else {
            responseArgsDto = interfaceDetailService.getResponseArgsItems(id);
        }
        responseargItems = responseArgsDto.getResponseargItems();
        settingRespDescAction(entity, responseargItems);
        interfaceMapper.updateResponseargsById(HWJacksonUtils.getJsonP(responseargItems), responseArgsDto.getId());
        return HWJacksonUtils.getJsonP(responseargItems);
    }


    @ResponseBody
    @RequestMapping(value = "/auto/settingByUrl/desc/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonAutoFillDescription2ByUrl(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(name = "entity", required = false) String entity
            , @RequestParam(name = "url", required = false) String url
            , @RequestParam(name = "requestmethod", required = false) String requestmethod) {
        String entityName = "nearbyHouse";
        if (ValueWidget.isNullOrEmpty(entity)) {
            entity = entityName;
        }
        List<ResponseArgsItem> responseargItems = null;
        ResponseArgsDto responseArgsDto = interfaceDetailService.getResponseArgsItemsByUrlAndMethod(url, requestmethod);
        responseargItems = responseArgsDto.getResponseargItems();
        settingRespDescAction(entity, responseargItems);
        interfaceMapper.updateResponseargsById(HWJacksonUtils.getJsonP(responseargItems), responseArgsDto.getId());
        return HWJacksonUtils.getJsonP(responseargItems);
    }

    private void settingRespDescAction(String entityName, List<ResponseArgsItem> responseargItems) {
        if (ValueWidget.isNullOrEmpty(responseargItems)) {
            System.out.println("responseargItems is null :" + responseargItems);
            return;
        }
        Map<String, String> descSimpleMap = getColumnDescMap(entityName);

        System.out.println(" :" + descSimpleMap);

        settingRespDesc2(descSimpleMap, responseargItems);
    }

    private static Map<String, String> getColumnDescMap(String entityName) {
        // 应答字段含义
        com.common.bean.RequestSendChain requestInfoBeanOrderObs = new com.common.bean.RequestSendChain();
        requestInfoBeanOrderObs.setServerIp("house.yhskyc.com");
        requestInfoBeanOrderObs.setSsl(false);
        requestInfoBeanOrderObs.setPort("80");
        requestInfoBeanOrderObs.setActionPath("/columnDescrip/map/json");
        requestInfoBeanOrderObs.setCustomRequestContentType("");
        requestInfoBeanOrderObs.setRequestMethod(com.common.dict.Constant2.REQUEST_METHOD_GET);
        // requestInfoBeanOrderObs.setDependentRequest(requestInfoBeanLogin);
        requestInfoBeanOrderObs.setCurrRequestParameterName("");
        requestInfoBeanOrderObs.setPreRequestParameterName("");

        java.util.TreeMap parameterMaprDIL = new java.util.TreeMap();//请求参数
        parameterMaprDIL.put("entity", entityName);
        parameterMaprDIL.put("simple", "true");
        requestInfoBeanOrderObs.setRequestParameters(parameterMaprDIL);
        requestInfoBeanOrderObs.updateRequestBody();

//                    org.apache.commons.collections.map.ListOrderedMap header=new org.apache.commons.collections.map.ListOrderedMap();
//                    requestInfoBeanOrderObs.setHeaderMap( header);

        com.common.bean.ResponseResult responseResultOrdermQN = requestInfoBeanOrderObs.request(); //new RequestPanel.ResponseResult(requestInfoBeanLogin).invoke();
        String responseOrderqdk = responseResultOrdermQN.getResponseJsonResult();
        System.out.println("responseText:" + responseOrderqdk);
        System.out.println(responseOrderqdk);
        ColumnDescResponseDto columnDescResponseDto = (ColumnDescResponseDto) HWJacksonUtils.deSerialize(responseOrderqdk, ColumnDescResponseDto.class);
        return columnDescResponseDto.getValue();
    }

    @ResponseBody
    @RequestMapping(value = "/desc/map/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonDescSimplemap2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(name = "descJson", required = false) String descJson) {
        Map<String, DescriptionDto> descriptionDtoMap = HWJacksonUtils.deSerializeMap(descJson, DescriptionDto.class);
        return HWJacksonUtils.getJsonP(parse2SimpleMap(descriptionDtoMap, null));
    }

    @ResponseBody
    @RequestMapping(value = "/query2/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonSettingDes3(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(required = false) String demo) {
        return null;
    }

    private void settingRespDesc2(Map<String, String> descSimpleMap, List<ResponseArgsItem> responseArgItems) {
        for (ResponseArgsItem responseArgsItem : responseArgItems) {
            if (ValueWidget.isNullOrEmpty(responseArgsItem.getChildren())) {
                if (ValueWidget.isNullOrEmpty(responseArgsItem.getDescription())) {
                    responseArgsItem.setDescription(descSimpleMap.get(responseArgsItem.getName()));
                }
            } else {
                settingRespDesc2(descSimpleMap, responseArgsItem.getChildren());
            }
        }
    }

    private void settingRespDesc(Map<String, DescriptionDto> descriptionDtoMap, List<ResponseArgsItem> responseArgItems) {
        if (ValueWidget.isNullOrEmpty(responseArgItems)) {
            return;
        }
        for (ResponseArgsItem responseArgsItem : responseArgItems) {
            if (ValueWidget.isNullOrEmpty(responseArgsItem.getChildren())) {
                if (ValueWidget.isNullOrEmpty(responseArgsItem.getDescription())) {
                    responseArgsItem.setDescription(findArgDesc(descriptionDtoMap, responseArgsItem.getName()));
                }
            } else {
                settingRespDesc(descriptionDtoMap, responseArgsItem.getChildren());
            }
        }
    }

    public Map<String, String> parse2SimpleMap(Map<String, DescriptionDto> descriptionDtoMap, LinkedHashMap<String, String> descMap) {
        if (null == descMap) {
            descMap = new LinkedHashMap<String, String>();

        }
        for (String key : descriptionDtoMap.keySet()) {
            DescriptionDto descriptionDto = descriptionDtoMap.get(key);
            descMap.put(key, descriptionDto.getDesc2());
            if (descriptionDto.getIsComplexColumn()) {
                parse2SimpleMap(descriptionDto.getChildren(), descMap);
            }

        }
        return descMap;
    }

    private String findArgDesc(Map<String, DescriptionDto> descriptionDtoMap, String columnName) {
        if (ValueWidget.isNullOrEmpty(descriptionDtoMap)) {
            return SystemHWUtil.EMPTY;
        }
        for (String key : descriptionDtoMap.keySet()) {
            DescriptionDto descriptionDto = descriptionDtoMap.get(key);
            if (columnName.equals(key)) {
                return descriptionDto.getDesc2();
            }
            String desc3 = findArgDesc(descriptionDto.getChildren(), columnName);
            if (!ValueWidget.isNullOrEmpty(desc3)) {
                return desc3;
            }
        }
        return null;
    }
}

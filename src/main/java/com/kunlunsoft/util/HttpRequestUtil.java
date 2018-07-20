package com.kunlunsoft.util;

import com.common.dict.Constant2;
import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.google.common.base.Splitter;
import com.io.hw.json.HWJacksonUtils;
import com.kunlunsoft.dto.ColumnDescResponseDto;
import com.kunlunsoft.handler.response.CacheResponseBodyAdvice;
import com.string.widget.util.ValueWidget;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtil {
    public static Logger logger = Logger.getLogger(CacheResponseBodyAdvice.class);
    public static org.slf4j.Logger httpClientRestLogger = LoggerFactory.getLogger("rest_log");

    /***
     * 把请求参数 附加到应答体 response中
     * @param request
     * @param json
     * @return
     */
    public static String appendQueryParamToBody(HttpServletRequest request, String json) {
        try {
            String queryString = WebServletUtil.getRequestQueryStr(request, SystemHWUtil.CHARSET_UTF);
            if (json.contains(Constant2.JSON_PLACEHOLDER_QUERYSTRING)
                    && !ValueWidget.isNullOrEmpty(queryString)) {
                Map<String, String> paramCanNOTPut = null;

                Exception exception = null;
                try {
                    paramCanNOTPut = Splitter.on("&").withKeyValueSeparator("=").split(queryString.replaceAll("&$", SystemHWUtil.EMPTY));
                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
                    exception = e;
                }
                if (ValueWidget.isNullOrEmpty(paramCanNOTPut)) {
                    if (null != exception) {
                        exception.printStackTrace();
                    }
                    return json;
                }
                Map<String, String> param = new HashMap<>(paramCanNOTPut);
                for (String key : param.keySet()) {
                    String val = param.get(key);
                    if (ValueWidget.isNullOrEmpty(val)) {
                        continue;
                    }
                    param.put(key, URLDecoder.decode(val, SystemHWUtil.CHARSET_UTF));
                }
                json = json.replace("\"" + Constant2.JSON_PLACEHOLDER_QUERYSTRING + "\"", HWJacksonUtils.getJsonP(param));
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("appendQueryParamToBody error", e);
        }
        return json;
    }

    public static Map<String, String> getColumnDescMap(String entityName) {
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

    public static Map<String, String> columnDescriptionByEntity(String entity) {
// 实体类的注释
        com.common.bean.RequestSendChain requestInfoBeanOrderYGA = new com.common.bean.RequestSendChain();
        requestInfoBeanOrderYGA.setServerIp("house.yhskyc.com");
        requestInfoBeanOrderYGA.setSsl(false);
        requestInfoBeanOrderYGA.setPort("80");
        requestInfoBeanOrderYGA.setActionPath("/" + ValueWidget.title(entity) + "/columnDescription/map/json");
        requestInfoBeanOrderYGA.setRequestCookie("JSESSIONID=8C49001E341F9152BD1D13712995FA79;conventionk=k1098bcd4-266b-47fa-881a-4e8c3ed0558b614923;");
        requestInfoBeanOrderYGA.setCustomRequestContentType("");
        requestInfoBeanOrderYGA.setRequestMethod(com.common.dict.Constant2.REQUEST_METHOD_GET);
        // requestInfoBeanOrderYGA.setDependentRequest(requestInfoBeanLogin);
        requestInfoBeanOrderYGA.setCurrRequestParameterName("");
        requestInfoBeanOrderYGA.setPreRequestParameterName("");

        java.util.TreeMap parameterMapgpRq = new java.util.TreeMap();//请求参数
        parameterMapgpRq.put("simple", "true");
        requestInfoBeanOrderYGA.setRequestParameters(parameterMapgpRq);
        requestInfoBeanOrderYGA.updateRequestBody();

//                    org.apache.commons.collections.map.ListOrderedMap header=new org.apache.commons.collections.map.ListOrderedMap();
//                    requestInfoBeanOrderYGA.setHeaderMap( header);

        com.common.bean.ResponseResult responseResultOrderOrK = requestInfoBeanOrderYGA.request(); //new RequestPanel.ResponseResult(requestInfoBeanLogin).invoke();
        String responseOrderNJW = responseResultOrderOrK.getResponseJsonResult();
        System.out.println("responseText:" + responseOrderNJW);
        System.out.println(responseOrderNJW);
        return HWJacksonUtils.deSerializeMap(responseOrderNJW, String.class);
//        ColumnDescResponseDto columnDescResponseDto = (ColumnDescResponseDto) HWJacksonUtils.deSerialize(responseOrderNJW, ColumnDescResponseDto.class);
//        return columnDescResponseDto.getValue();
        //    System.out.println(com.io.hw.json.JSONHWUtil.jsonFormatter( responseOrderNJW));

    }
}

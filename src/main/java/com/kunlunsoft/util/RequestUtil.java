package com.kunlunsoft.util;

import com.common.dict.Constant2;
import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.google.common.base.Splitter;
import com.io.hw.json.HWJacksonUtils;
import com.kunlunsoft.handler.response.CacheResponseBodyAdvice;
import com.string.widget.util.ValueWidget;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
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

}

package com.kunlunsoft.handler.response;

import com.common.util.WebServletUtil;
import com.kunlunsoft.util.HttpRequestUtil;
import com.string.widget.util.ValueWidget;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/***
 * added at 2018-03-20<br />
 * 接口应答缓存管理
 * 功能:缓存接口的返回,即注解 @ResponseBody Action方法 返回的json<br />
 * 利用redis 缓存接口返回,在CommonHandlerInterceptor中获取并使用缓存<br />
 * 被 annotation/ResponseBodyAdviceChain.java 调用<br />
 * 会读取配置文件:"config/responsebody.advice.exclude" <br />
 *  response应答码不是200时,没有调用 CacheResponseBodyAdvice
 */
@ControllerAdvice
public class CacheResponseBodyAdvice implements ResponseBodyAdvice {
    public static Logger logger = Logger.getLogger(CacheResponseBodyAdvice.class);
    public static org.slf4j.Logger httpClientRestLogger = LoggerFactory.getLogger("rest_log");
    /***
     * 包含在 filterPathList 中的接口 ,将不会缓存返回结果
     */
    static List<String> filterPathList = new ArrayList<String>();


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return (null != returnType.getMethodAnnotation(ResponseBody.class))
                || (null != returnType.getParameterAnnotation(ResponseBody.class));
    }

    /***
     * 参数useAPICache true表示使用缓存 <br />
     * notice 注意:404 or 400 等错误码,不会调用该方法
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        System.out.println("filterPathList :" + filterPathList.toString());
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
        HttpServletRequest httpServletRequest = servletServerHttpRequest.getServletRequest();
        String servletPath = httpServletRequest.getServletPath();

        //如果请求特别耗时,请求响应时间超过4秒,则设置缓存<br />
        //  see com/common/web/filter/RequestbodyFilter.java 中 cacheWhenOvertime
        // 此时并没有保存到 redis,仅仅存储到 HttpServletRequest  中
        WebServletUtil.backUpResponseResult(body, httpServletRequest, servletPath);

        if (ValueWidget.isNullOrEmpty(body)) {
            return body;
        }
        String json = (String) body;
        //把请求参数 附加到应答体 response中
        json = HttpRequestUtil.appendQueryParamToBody(servletServerHttpRequest.getServletRequest(), json);
        if (!ValueWidget.isNullOrEmpty(json)) {
            body = json;
        }

        //包含在 filterPathList 中的接口 ,将不会缓存返回结果
        if (filterPathList.contains(servletPath)) {
            return body;
        }


        String message = "beforeBodyWrite 调用一次" + body;
        logger.warn(message);
//        httpClientRestLogger.error(message);
//        System.out.println(" :" + message);
        return body;
    }


}

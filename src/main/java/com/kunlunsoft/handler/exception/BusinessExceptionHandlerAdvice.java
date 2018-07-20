package com.kunlunsoft.handler.exception;

import com.common.bean.callback.ICommonCallback;
import com.common.bean.exception.LogicBusinessException;
import com.common.util.BusinessExceptionUtil;
import com.common.util.SystemHWUtil;
import com.common.util.WebServletUtil;
import com.kunlunsoft.util.RequestUtil;
import com.string.widget.util.ValueWidget;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by whuanghkl on 3/30/16.
 * 注意使用注解@ControllerAdvice作用域是全局Controller范围
 * 可应用到所有@RequestMapping类或方法上的@ExceptionHandler、@InitBinder、@ModelAttribute，在这里是@ExceptionHandler<br />
 * 用于检测第三方接口,比如bsvc或cia的504,502等异常<br />
 * 这些异常均属于非业务异常,与业务毫无关系,所以单独处理<br />
 * 注意:StoreBusinessException 不要捕获,否则无法被BusinessExceptionHandlerAdvice 截获<br />
 * 注意:传递url中的参数如果可能包含中文一定要URL编码, <br />
 * 待解决的问题:在springBoot中,出现异常被 BusinessExceptionHandlerAdvice 处理,则不会走 CacheResponseBodyAdvice <br />
 */
@ControllerAdvice
public class BusinessExceptionHandlerAdvice {
    public static Logger logger = Logger.getLogger(BusinessExceptionHandlerAdvice.class);

    @ExceptionHandler(LogicBusinessException.class)
//    @RESPONSE_CONTENTTYPE_JSON_UTFStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
    public String handleBusinessException(LogicBusinessException ex, HttpSession session, final HttpServletRequest request, HttpServletResponse response) {
//        return ClassUtils.getShortName(ex.getClass()) + ex.getMessage();
        logger.error(ex.getErrorMessage(), ex);//{errorCode='1021', errorMessage='用户不在组织的企业客户身份中'}
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        logger.error("old url:" + request.getRequestURL());
        String requestParameter = request.getQueryString();//GET 请求
        if (ValueWidget.isNullOrEmpty(requestParameter)) {
            requestParameter = WebServletUtil.getRequestPostStr(request);//POST请求
        }
        logger.error("query string:" + requestParameter);
        //打印请求头 request header
        Map headerMap = WebServletUtil.getHeaderMap(request, new String[]{"cookie"});
        String headerStr = headerMap.toString();
        String headerMsg = "请求头:" + headerStr;
        logger.error(headerMsg);
        System.out.println(headerMsg);
        /*StackTraceElement stackTraceElement=stackTraceElements[0];

        System.out.println("00 :" + isControllerAction(stackTraceElement));
        System.out.println("11 :" + isControllerAction(stackTraceElements[1]));
        System.out.println("0 :" +stackTraceElement.getMethodName());
        System.out.println("1 :" +stackTraceElements[1].getClass());*/
        BusinessExceptionUtil.dealException(ex, response, new ICommonCallback() {
            @Override
            public Object callback(Object... objects) {
                return //把请求参数 附加到应答体 response中
                        RequestUtil.appendQueryParamToBody(request, (String
                                ) objects[0]);
            }
        });

        return null;
    }

    /***
     *  不好使<br />
     * 见下面的方法 <code>handleNotFound404Exception2</code>
     * @return
     */
    @ExceptionHandler({org.springframework.http.converter.HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String resolveException() {
        return "error2222";
    }

    /***
     * 响应400错误
     * @param ex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(org.springframework.beans.TypeMismatchException.class)
    public String handle400Exception2(org.springframework.beans.TypeMismatchException ex, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        return handle400Action(ex, ex.getValue(), request, response);
    }

    @ExceptionHandler({IllegalStateException.class})
    public String handle400Exception3(IllegalStateException ex, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        return handle400Action(ex, null, request, response);
    }

    @ExceptionHandler({BindException.class})
    public String handle400Exception4(BindException ex, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String respCode = String.valueOf(HttpServletResponse.SC_BAD_REQUEST);
        logger.error(respCode, ex);
        LogicBusinessException logicBusinessException = new LogicBusinessException();
        logicBusinessException.setErrorCode(respCode);
        String errorMessage = "请确认 API接口路径中 \"{status}\"中的占位符不是成员变量名称,";
        System.out.println("errorMessage :" + errorMessage);
        logger.error(errorMessage);
        logicBusinessException.setErrorMessage(errorMessage + ex.getMessage());
        BusinessExceptionUtil.dealException(logicBusinessException, response, null);
        return null;
    }

    private String handle400Action(RuntimeException ex, Object value, HttpServletRequest request, HttpServletResponse response) {
        String respCode = String.valueOf(HttpServletResponse.SC_BAD_REQUEST);
        logger.error(respCode, ex);
        LogicBusinessException logicBusinessException = new LogicBusinessException();
        logicBusinessException.setErrorCode(respCode);
        logicBusinessException.setErrorMessage((value == null ? "" : value + " ") + ex.getMessage());
        BusinessExceptionUtil.dealException(logicBusinessException, response, null);
        return null;
    }

    /***
     * 响应404 错误
     * @param ex
     * @param session
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
//org.springframework.web.servlet.NoHandlerFoundException: No handler found for GET /agent2/follow/query/json, headers={host=[127.0.0.1:8080], connection=[keep-alive], upgrade-insecure-requests=[1], user-agent=[Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.167 Safari/537.36], accept=[text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8], accept-encoding=[gzip, deflate, br], accept-language=[zh-CN,zh;q=0.9,en;q=0.8], cookie=[Hm_lvt_338fa58da093fe8c8cfbbcb1b1ca9854=1493706802; Hm_lvt_a4980171086658b20eb2d9b523ae1b7b=1496945290; CLI_LONG_BD_ID=380bdb8a-4d94-990e-e6bc-6b8764ec8f1e; JSESSIONID=684808D644E46125AF6DEF447C57515B]}
    public String handleNotFound404Exception2(org.springframework.web.servlet.NoHandlerFoundException ex, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //判断 response code是否是404,因为如果是404,则接口response 响应特别特别慢,极慢

        String respCode = "404";
        logger.error(respCode, ex);
        LogicBusinessException logicBusinessException = new LogicBusinessException();
        logicBusinessException.setErrorCode(respCode);
        logicBusinessException.setErrorMessage(ex.getRequestURL() + " " + SystemHWUtil.splitAndFilterString(ex.getMessage(), 60));
        BusinessExceptionUtil.dealException(logicBusinessException, response, null);
        return null;
    }


}

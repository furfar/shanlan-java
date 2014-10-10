package com.shanlan.opf.infra;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.common.exception.sub.business.*;
import com.shanlan.opf.application.dto.ErrorResponseDTO;
import com.shanlan.opf.application.dto.RequestDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.shanlan.common.constant.ConstantString;
import com.shanlan.common.util.ReflectionUtils;
import com.shanlan.opf.application.dto.SuccessResponseDTO;
import com.shanlan.opf.core.domain.Request;

@Named
public class InvokeHelper {
    @Inject
    private static HttpClient httpClient;

    private static final Logger logger = LoggerFactory
            .getLogger(InvokeHelper.class);

    public static SuccessResponseDTO getResponse(String URI, String param) {

        SuccessResponseDTO successResponse = new SuccessResponseDTO();

        // 创建httppost
        HttpPost httppost = new HttpPost(URI);
        // 创建参数队列
        List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        postParams.add(new BasicNameValuePair(ConstantString.PARAM, param));
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(postParams, ConstantString.STRING_ENCODING_UTF8);
            httppost.setEntity(uefEntity);
            if (logger.isDebugEnabled()) {
                logger.debug("executing request " + httppost.getURI());
            }
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                String responseString = EntityUtils.toString(entity,
                        ConstantString.STRING_ENCODING_UTF8);

                if (logger.isDebugEnabled()) {
                    logger.debug("Response content: " + responseString);
                }

                successResponse = JSONObject.parseObject(responseString,
                        SuccessResponseDTO.class);
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }

        return successResponse;
    }


    /**
     * @param request
     * @return
     */
    public static Map<String, String> getParamMap(Request request) {
        Map<String, String> paramMap = new HashMap<String, String>();

        List<Field> fields = ReflectionUtils
                .getSelfAndDirectParentDeclaredFields(request);

        for (Field field : fields) {// 通过“反射”将参数名和参数值存入Map中

            field.setAccessible(true); // 要想访问private字段，需要先将其Accessible属性设置为true
            Object fieldValue = null;
            try {
                fieldValue = field.get(request);
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }

            paramMap.put(field.getName(), (String) fieldValue);
        }
        return paramMap;
    }


    /**
     * 解析并检查传入的请求参数
     *
     * @param request
     * @return
     * @throws com.shanlan.common.exception.sub.business.RequestParameterException
     */
    public static RequestDTO parseRequestParameter(String request)
            throws RequestParameterException {

        RequestDTO requestDTO = new RequestDTO();

        try {

            requestDTO = JSONObject.parseObject(request, RequestDTO.class);

        } catch (JSONException e) {
            throw new RequestFormatException(e.getMessage());
        }

        List<Field> fields = ReflectionUtils
                .getSelfAndDirectParentDeclaredFields(requestDTO);

        for (Field field : fields) {// 通过“反射”检查所有的字段是否成功完成映射

            field.setAccessible(true); // 要想访问private字段，需要先将其Accessible属性设置为true
            Object fieldValue = null;
            try {
                fieldValue = field.get(requestDTO);
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
            if (fieldValue == null) { // 如果字段的值为null，说明JSON到Java对象的映射过程出现异常
                String fieldName = field.getName();

                throw new RequestMappingException("json key '" + fieldName
                        + "' does not exist, plesse check it.");
            }

        }

        return requestDTO;

    }


    public static ErrorResponseDTO handleException(Exception e) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO();

        if (e instanceof RequestFormatException) {
            errorResponse.setCode("101");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestMappingException) {
            errorResponse.setCode("201");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestAuthenticationException) {
            errorResponse.setCode("301");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestAuthorizationException) {
            errorResponse.setCode("401");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestParameterException) {
            errorResponse.setCode("501");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof ServiceDisableException) {
            errorResponse.setCode("601");
            errorResponse.setMessage(e.getMessage());
        }

        else if (e instanceof RequestCheckingException) {
            errorResponse.setCode("601");
            errorResponse.setMessage(e.getMessage());
        } else {
            errorResponse.setCode("999");
            errorResponse.setMessage(e.getMessage());
        }

        return errorResponse;

    }

}

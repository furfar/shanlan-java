package com.shanlan.common.util;

import com.shanlan.common.constant.ConstantPunctuation;
import com.shanlan.common.constant.ConstantString;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by albertliu on 14/10/24.
 */
public class StringUtil extends StringUtils {


    public static Map<String, String> parseCookie(String cookieString) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        String[] cookies = cookieString.split(ConstantPunctuation.SEMICOLON);
        for (String cookie : cookies) {
            String key = cookie.trim().split(ConstantPunctuation.EQUAL)[0];
            String vale = cookie.trim().split(ConstantPunctuation.EQUAL)[1];
            cookieMap.put(key.trim(), vale.trim());
        }
        return cookieMap;
    }


    public static String parseNodeJsCookie(String initialValue) {
        String parsedCookie = "";
        if (StringUtils.isNotBlank(initialValue)) {
            parsedCookie = initialValue.substring(4, 36);
        }

        return parsedCookie;
    }

    public static String getNodeJsSession(String cookieString) {
        Map<String, String> cookieMap = parseCookie(cookieString);
        String cookie = cookieMap.get(ConstantString.NODE_JS_COOKIE_KEY);
        String parsedNodeJsCookie = parseNodeJsCookie(cookie);
        return ConstantString.REDIS_KEY_PREFIX_COOKIE + parsedNodeJsCookie;
    }

}

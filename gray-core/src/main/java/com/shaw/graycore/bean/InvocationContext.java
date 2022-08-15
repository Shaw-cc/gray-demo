package com.shaw.graycore.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 灰度标识上下文
 * @Author: Shaw
 * @Date: 2022/8/15 00:39
 */
public class InvocationContext {

    static ThreadLocal<Map<String, Object>> invocation = new ThreadLocal<>();

    public static void setInvocation(String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        invocation.set(map);
    }

    public static void remove() {
        invocation.remove();
    }

    public static Map<String, Object> getInvocation() {
        return invocation.get();
    }
}


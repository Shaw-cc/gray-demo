package com.shaw.graycore.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.shaw.graycore.GrayProperties;
import com.shaw.graycore.bean.GrayRule;
import com.shaw.graycore.bean.InvocationContext;
import com.shaw.graycore.match.AbstractMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

/**
 * @Description: 灰度服务
 * @Author: Shaw
 * @Date: 2022/8/15 00:15
 */
@Service
public class GrayService {

    @Autowired
    GrayProperties grayProperties;

    /**
     * 从灰度配置中心下载灰度规则
     *
     * @param ruleName 灰度规则名称
     * @return 灰度规则
     */
    public GrayRule query(String ruleName) {
        String url = grayProperties.getUrl() + "/query";
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("ruleName", ruleName);
        String jsonStr = HttpUtil.get(url, map);
        return JSON.parseObject(jsonStr, GrayRule.class);
    }

    /**
     * 从上下文中获取参数,进行灰度匹配
     *
     * @return 流量是否进行灰度转发
     */
    public boolean isMatch() {
        GrayRule rule = query(grayProperties.getServiceRuleName());
        String value = Optional.ofNullable(InvocationContext.getInvocation()).map(map -> map.getOrDefault(rule.getParameter(), "")).orElse("").toString();
        return AbstractMatcher.isGrayMatch(rule, value);
    }
}

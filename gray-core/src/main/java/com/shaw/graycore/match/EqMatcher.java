package com.shaw.graycore.match;

import lombok.AllArgsConstructor;

/**
 * @Description: 等值匹配器
 * @Author: Shaw
 * @Date: 2022/8/15 00:04
 */
@AllArgsConstructor
public class EqMatcher extends AbstractMatcher {

    /**
     * 灰度配置中心配置的值
     */
    String parameter;


    @Override
    public boolean isMatch(String value) {
        return parameter.equals(value);
    }
}

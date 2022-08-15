package com.shaw.graycore.match;

/**
 * @Description: 匹配接口
 * @Author: Shaw
 * @Date: 2022/8/15 00:01
 */
public interface BaseMatcher {

    String getMatcherName();

    boolean isMatch(String value);

}

package com.shaw.graycore.match;

import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

/**
 * @Description: 正则匹配器
 * @Author: Shaw
 * @Date: 2022/8/15 00:51
 */
@AllArgsConstructor
public class PatternMatcher extends AbstractMatcher {

    private String regex;

    @Override
    public boolean isMatch(String value) {
        return Pattern.matches(regex, value);
    }
}

package com.shaw.graycore.match;

import com.shaw.graycore.bean.GrayRule;

/**
 * @Description: 匹配器
 * @Author: Shaw
 * @Date: 2022/8/15 00:49
 */
public abstract class AbstractMatcher implements BaseMatcher {

    static BaseMatcher get(GrayRule grayRule) {
        switch (grayRule.getMatch()) {
            case "EqMatcher":
                return new EqMatcher(grayRule.getValue());
            case "PatternMatcher":
                return new PatternMatcher(grayRule.getValue());
        }
        return null;
    }

    public static boolean isGrayMatch(GrayRule rule, String value) {
        return get(rule).isMatch(value);
    }

    @Override
    public String getMatcherName() {
        return this.getClass().getSimpleName();
    }
}

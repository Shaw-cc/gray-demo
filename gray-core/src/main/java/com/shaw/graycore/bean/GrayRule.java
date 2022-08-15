package com.shaw.graycore.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 灰度规则
 * @Author: Shaw
 * @Date: 2022/8/14 23:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrayRule {

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 位置
     * header cookie body
     */
    private String position;

    /**
     * 参数名称
     * userID
     */
    private String parameter;

    /**
     * 代比较的参数值
     * 001
     */
    private String value;

    /**
     * 比较类型
     * eq 正则 大于小于
     */
    private String match;

}

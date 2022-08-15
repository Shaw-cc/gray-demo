package com.shaw.etcd.controller;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.shaw.graycore.bean.GrayRule;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 模拟灰度规则配置中心
 * @Author: Shaw
 * @Date: 2022/8/14 23:53
 */
@RestController
public class GrayController {

    LoadingCache<String, GrayRule> cache = CacheBuilder.newBuilder().maximumSize(100).build(
            new CacheLoader<String, GrayRule>() {
                @Override
                public GrayRule load(String s) {
                    return GrayRule.builder()
                            .ruleName("GrayRule")
                            .position("header")
                            .parameter("teamId")
                            .value("100")
                            .match("EqMatcher")
                            .build();
                }
            }
    );


    @PostMapping("/create")
    public void createRule(@RequestBody GrayRule grayRule) {
        cache.put(grayRule.getRuleName(), grayRule);
    }

    @SneakyThrows
    @GetMapping("/query")
    public GrayRule createRule(String ruleName) {
        return cache.get(ruleName);
    }

}

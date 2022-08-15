package com.shaw.graycore;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:gray.properties"})
@Data
public class GrayProperties {

    @Value("${gray.url}")
    private String url;

    @Value("${gray.rule.service-name}")
    private String serviceRuleName;
}
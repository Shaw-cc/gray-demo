package com.shaw.etcd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EtcdApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtcdApplication.class, args);
    }

}

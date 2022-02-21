package com.will.localcache.localcacheserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class LocalCacheServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalCacheServerApplication.class, args);
    }

}

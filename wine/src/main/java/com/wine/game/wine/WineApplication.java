package com.wine.game.wine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 
 *          ________
 *          ___  __/______________
 *          __  /_ ___  __ \  ___/
 *          _  __/ __  /_/ / /__
 *          /_/    _  .___/\___/
 *                 /_/
 * @author: zeno
 *
 * @create: 2022-03-08 12:14
 */

@MapperScan("com.wine.game.wine.dao")
@SpringBootApplication
public class WineApplication {
    public static void main(String[] args) {
        SpringApplication.run(WineApplication.class,args);
    }
}
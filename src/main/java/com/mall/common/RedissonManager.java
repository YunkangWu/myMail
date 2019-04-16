package com.mall.common;

import com.mall.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Redisson初始化
 */
@Component
@Slf4j
public class RedissonManager {
    private Config config = new Config();

    private Redisson redisson = null;

    public Redisson getRedisson() {
        return redisson;
    }

    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port", "6379"));
    private static String redis2Ip = PropertiesUtil.getProperty("redis2.ip");
    private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getProperty("redis2.port", "6380"));

    @PostConstruct
    private void init() {
        try {
            config.useSingleServer().setAddress("http://"+redis1Ip + ":" + redis1Port);
            redisson = (Redisson) Redisson.create(config);
            log.info("初始化redisson结束");
        } catch (Exception e) {
            log.error("redisson init error", e);
        }
    }
}

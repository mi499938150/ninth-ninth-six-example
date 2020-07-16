package com.mi.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.KSQLWindow;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author : Rong
 * @date : 2020/7/15
 * @Desc: 缓存管理器配置类
 */

@Configuration
@EnableCaching
@Slf4j
public class CaffeineCacheConfig {

    /**
     * CacheManager 实现类
     * @return
     */
    @Bean("cacheManager")
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager =
                new SimpleCacheManager();
        //缓存集合
        ArrayList<CaffeineCache> caffeineCaches  =
                new ArrayList<>();
        caffeineCaches.add(new CaffeineCache("users-cache",
                Caffeine.newBuilder()
                .maximumSize(1000)
                        //最后一次访问 120 秒 过期
                .expireAfterAccess(120, TimeUnit.SECONDS)
                .build()
                ));
        cacheManager.setCaches(caffeineCaches);
        return  cacheManager;
    }
}
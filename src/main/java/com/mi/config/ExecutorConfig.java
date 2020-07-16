package com.mi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : Rong
 * @date : 2020/7/16
 * @Desc: 线程池配置
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    /**
     * 定义导出服务线程池
     * @return
     */
    @Bean("exportServiceExecutor")
    public Executor exportServiceExeutor(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程数量 :  当前机器的核心数
        executor.setCorePoolSize(
                Runtime.getRuntime().availableProcessors()
        );

        executor.setMaxPoolSize(
                Runtime.getRuntime().availableProcessors() * 2
        );
        // 队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //线程池中的线程名前缀
        executor.setThreadNamePrefix("export-");
        // 拒绝策略: 直接拒绝
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.AbortPolicy()
        );
        //执行初始化
        executor.initialize();
        return executor;
    }
}
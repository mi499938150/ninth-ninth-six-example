package com.mi.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc:
 */
@Configuration
public class MybatisPlusConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor =
                new PaginationInterceptor();

        // 数据库类型配置
        paginationInterceptor.setDialectType(String.valueOf(DbType.MYSQL));
        return paginationInterceptor;
    }


    /**
     * 乐观锁使用的规则
     * 1. 如果更新数据肿不带version字段；不使用乐观锁，并且version不会累加
     * 2. 如果更新字中带又version ，但与数据库中不一致,更新失败
     * 3. 如果带有version,并且与数据库中一致，更新成功,并且version不会累加
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
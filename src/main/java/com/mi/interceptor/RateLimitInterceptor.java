package com.mi.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.mi.exception.BusinessException;
import com.mi.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Rong
 * @date : 2020/7/15
 * @Desc: 全局限流拦截器
 */
@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor{

    /**
     * 限流器实例
     */
    private static final RateLimiter reteLimiter =
            RateLimiter.create(1);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)throws Exception{

        if (!reteLimiter.tryAcquire()){
            log.error("系统已被限流...");
            throw  new BusinessException(ErrorCodeEnum.RATE_LIMIT_ERROR);
        }
        return true;
    }
}
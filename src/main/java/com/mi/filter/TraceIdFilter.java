package com.mi.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author : Rong
 * @date : 2020/7/15
 * @Desc: TraceId 过滤器
 */
@WebFilter
@Order(1)
public class TraceIdFilter implements Filter {

    /**
     * TraceId 常量
     */
    private static final String TRACE_ID = "traceId";

    /**
     * TraceId 常量
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String traceId = servletRequest.getParameter(TRACE_ID);
        // 为空设置默认值
        if (StringUtils.isEmpty(traceId)){
            traceId = UUID.randomUUID().toString();
        }
        // 在MDC中放入traceId
        MDC.put(TRACE_ID,traceId);

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
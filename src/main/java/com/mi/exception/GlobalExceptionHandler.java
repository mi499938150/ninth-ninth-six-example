package com.mi.exception;

import com.mi.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : Rong
 * @date : 2020/7/14
 * @Desc: 全局异常捕获处理器
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult businessExceptionHandle(BusinessException e){
        log.error("捕捉到业务类异常:",e);
        return ResponseResult.failure(e.getCode(),e.getMessage());
    }


    /**
     * 拦截运行时异常
     */

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeExceptionHandle(RuntimeException e){
        log.error("捕捉到运行时异常",e);
        return ResponseResult.failure(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),
                e.getMessage());
    }

    /**
     * 捕捉系统异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult throwableHandle(Throwable throwable){
        log.error("捕捉Throwble异常",throwable);
        return ResponseResult.failure(
                ErrorCodeEnum.SYSTEM_ERROR.getCode(),
                throwable.getMessage()
        );
    }


}
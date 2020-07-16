package com.mi.exception;


import lombok.Getter;

/**
 * @author : Rong
 * @date : 2020/7/14
 * @Desc: 业务类异常
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常编码
     */


    @Getter
    private final String code;

    /**
     * 根据枚举构造业务异常
     */
    public BusinessException(ErrorCodeEnum error) {
        super(error.getMessage());
        this.code = error.getCode();
    }
    /**
     * 自定义消息体构造业务类异常
     */
    public BusinessException(ErrorCodeEnum error,String message){
        super(message);
        this.code = error.getCode();
    }

    /**
     * 根据异常构业务类
     * @param error
     * @param throwable
     */
    public BusinessException(ErrorCodeEnum error , Throwable throwable){
        super(throwable);
        this.code = error.getCode();
    }
}
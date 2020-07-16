package com.mi.common;

import com.mi.exception.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc: 通用返回结果模型
 */
@Data
public class ResponseResult<T> implements Serializable {


    private static final long serialVersionUID = 7799380036101808301L;

    /**
     * 是否成功
     */
    private Boolean success;


    /**
     * 编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 结果
     */
    private T result;

    public static <T> ResponseResult<T> success(T result){
        ResponseResult<T> responseResult =
                new ResponseResult<>();
        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);
        return responseResult;
    }

    public static <T> ResponseResult<T> failure(String code,String message){
        ResponseResult<T> responseResult =
                new ResponseResult<>();
        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }

    /**
     * 失败
     * @param errorCodeEnum
     * @param <T>
     * @return
     */
    public static<T> ResponseResult<T> failure(ErrorCodeEnum errorCodeEnum) {

        return     failure(errorCodeEnum.getCode(),errorCodeEnum.getMessage());
    }
}
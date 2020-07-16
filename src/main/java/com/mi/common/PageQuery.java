package com.mi.common;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc: 通用分页查询对象
 */
@Data
public class PageQuery<T> implements Serializable{

    private static final long serialVersionUID = -2247971449641668767L;

    /**
     * 当前页
     */
    @NotNull(message = "页号不能为空！")
    @Min(value = 1,message = "页号必须为正数！")
    private Integer pageNo = 1;


    /**
     *  每页条数
     */
    @NotNull(message = "煤业条数不能为空")
    @Max(value = 100,message = "每页条数不能超过100条！")
    private Integer pageSize = 10;


    /**
     * 动态查询条件
     */
    @Valid
    @NotNull(message = "动态查询条件不能为空!")
    private T query;
}
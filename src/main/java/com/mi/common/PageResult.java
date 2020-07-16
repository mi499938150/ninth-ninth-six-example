package com.mi.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc: 通用分页查询返回实体
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 26305648006432481L;

    /**
     * 当前页号
     */
    private Integer pageNo;

    /**
     * 每页行数
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pagNum;

    /**
     * 动态的内容
     */
    private T data;
}
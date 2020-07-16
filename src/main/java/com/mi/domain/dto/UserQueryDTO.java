package com.mi.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc:
 */
@Data
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = -5674479992377518219L;

    /**
     * 用户名
     */

    @NotEmpty(message = "用户姓名不能为空！")
    private String username;



}
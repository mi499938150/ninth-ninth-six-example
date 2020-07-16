package com.mi.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc:
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 8649201870046845166L;


    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */

    private String email;

    /**
     * 手机号
     */
    private String phone;
}
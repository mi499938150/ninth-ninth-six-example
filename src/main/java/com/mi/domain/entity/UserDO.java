package com.mi.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Rong
 * @date : 2020/7/12
 * @Desc: 用户DO实体
 */
@Data
@TableName("user")
public class UserDO implements Serializable {


    private static final long serialVersionUID = 8685932532983444810L;

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

    /**  系统主信息 **/
    /**
     * 数据库主键
     */
    private Long id;

    /**
     * 数据的创建时间
     */
    private LocalDateTime created;

    /**
     * 数据修改时间
     */
    private LocalDateTime modified;

    /**
     * 创建者
     */
    private String creator;

    /**
     *  最后修改者
     */
    private String operator;

    /**
     * 逻辑删除字段 0:正常, 1: 逻辑删除
     */
    private Integer status;

    /**
     *  版本号
     */
    private Long version;
}
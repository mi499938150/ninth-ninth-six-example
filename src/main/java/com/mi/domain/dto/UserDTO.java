package com.mi.domain.dto;

import com.mi.util.InsertValidationGroup;
import com.mi.util.UpdateValidationGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Rong
 * @date : 2020/7/12
 * @Desc: 用户DTO实体
 */
@Data
public class UserDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7389551464434176698L;


    /**
     *  ID
     */
    private Integer id;

    /**
     * 用户名
     */

    @NotBlank(message = "用户名不能为空!",
             groups = {InsertValidationGroup.class})
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户名不能为空!",
            groups = {InsertValidationGroup.class})
    @Length(min = 6, max = 18,
            message = "密码长度不能少于6位，不能多于18位")
    private String password;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空!"
             ,groups = {InsertValidationGroup.class})
    @Max(value = 60,message = "年龄不能大于60岁")
    @Min(value = 18,message = "年龄不能小于18岁")
    private Integer age;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空!",
             groups = {InsertValidationGroup.class})
    @Email(message = "必须为有效邮箱！！")
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空!",
        groups = {InsertValidationGroup.class})
    private String phone;


    /**
     *  版本号
     */
    @NotNull(message = "版本号不能为空!",
         groups = {UpdateValidationGroup.class})
    private Long version;

    /**
     * 创建时间
     */
    private LocalDateTime create;


}
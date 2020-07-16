package com.mi.util;


import com.mi.exception.ErrorCodeEnum;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author : Rong
 * @date : 2020/7/14
 * @Desc:
 */
public class ValidatorUtils {

    /**
     * 校验器
     */

    private static Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();
    public static <T> void  validate(T object,Class... groups){
        Set<ConstraintViolation<T>> validate =
                validator.validate(object,groups);
        if (!CollectionUtils.isEmpty(validate)){
            StringBuilder exceptionMessage =
                    new StringBuilder();
             validate.forEach(constraintViolation -> {
                 exceptionMessage.append(constraintViolation.getMessage());
             });
             throw new RuntimeException(
                     exceptionMessage.toString());
        }
    }
}
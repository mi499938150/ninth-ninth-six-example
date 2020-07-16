package com.mi.controller;

import com.mi.common.PageQuery;
import com.mi.common.PageResult;
import com.mi.common.ResponseResult;
import com.mi.domain.dto.UserDTO;
import com.mi.domain.dto.UserQueryDTO;
import com.mi.domain.vo.UserVO;
import com.mi.exception.ErrorCodeEnum;
import com.mi.service.ExcelExportService;
import com.mi.service.UserService;
import com.mi.util.InsertValidationGroup;
import com.mi.util.UpdateValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc: yoghurtController
 */

@RestController
// 开启验证注解
@Validated
@Slf4j
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private ExcelExportService excelExportService;

    /**
     * POST /api/users UserDTO
     * 新建用户
     */
    @CacheEvict(cacheNames = "users-cache",allEntries = true)
    @PostMapping
    public ResponseResult  save(
            @Validated(InsertValidationGroup.class)
            @RequestBody UserDTO userDTO){

        int save = userService.save(userDTO);
        if (save == 1 ){
            return ResponseResult.success("新增成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }

    }

    /**
     * 更新用户信息
     * PUT /api/users/{id} UserDTO userDTO
     */

    @PutMapping("/{id}")
    public ResponseResult update(@NotNull @PathVariable("id") Long id,
                                 @Validated(UpdateValidationGroup.class)
                                 @RequestBody UserDTO usetDTO){

        int update = userService.update(id,usetDTO);
        if (update == 1) {
            return ResponseResult.success("更新成功!");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.UPDATE__FAILURE);
        }

    }


    /**
     * 删除用户信息
     * DELECT /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(
            @NotNull(message = "用户ID不能为空！")
            @PathVariable("id") Long id){
        int delete  = userService.delete(id);

        if (delete == 1 ) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE__FAILURE);
        }
    }

    /**
     * 查询用户信息
     * GET
     * @return
     */
    @Cacheable(cacheNames = "users-cache")
    @GetMapping
    public ResponseResult<PageResult> query(
            @NotNull Integer pageNo,
            @NotNull Integer pageSize,
            @Validated UserQueryDTO query){


        log.info("未使用缓存");
        //构造查询条件
        PageQuery<UserQueryDTO> pageQuery =
                new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(query);
        //查询
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);
        // 实体转换
        List<UserVO> userVOList = Optional.ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO =
                            new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);
                    userVO.setPassword("*******");
                    if (!StringUtils.isEmpty(userDTO.getPhone())){
                        userVO.setPhone(userDTO.getPhone()
                                .replace("(\\d{3})\\d{4}(\\d{4})",
                                "$1****$2"));
                    }

                    return userVO;
                }).collect(Collectors.toList());
        // 封装返回结果
        PageResult<List<UserVO>> result =
                new PageResult<>();
        BeanUtils.copyProperties(pageResult,result);
        result.setData(userVOList);
        return ResponseResult.success(result);
    }

    @GetMapping("/export")
    public ResponseResult<Boolean> export(@Validated UserQueryDTO queryDTO,
                                          @NotEmpty String filename){

        log.info("接受到导出请求! filename = {}",filename);
        // 数据导出
        excelExportService.export(queryDTO,filename);
        //异步导出
//        excelExportService.asyncExport(queryDTO,filename);
        return ResponseResult.success(Boolean.TRUE);
    }

}
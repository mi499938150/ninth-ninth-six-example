package com.mi.service;

import com.mi.common.PageQuery;
import com.mi.common.PageResult;
import com.mi.domain.dto.UserDTO;
import com.mi.domain.dto.UserQueryDTO;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc: 用户服务接口
 */
public interface UserService {

    /**
     * 新增
     * @param userDTO
     * @return
     */
    int save(UserDTO userDTO);


    /**
     * 更新
     * @param id
     * @param userDTO
     * @return
     */
    int update(Long id,UserDTO userDTO);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);


    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);
}

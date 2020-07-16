package com.mi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mi.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc: UserMapper
 */

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
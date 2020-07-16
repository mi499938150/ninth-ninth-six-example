package com.mi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.common.PageQuery;
import com.mi.common.PageResult;
import com.mi.domain.dto.UserDTO;
import com.mi.domain.dto.UserQueryDTO;
import com.mi.domain.entity.UserDO;
import com.mi.mapper.UserMapper;
import com.mi.service.UserService;
import com.mi.util.ValidatorUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc: 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        // TODO 浅拷贝 属性名相同才能拷贝
        BeanUtils.copyProperties(userDTO,userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO,userDO);
        userDO.setId(id);
        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {

       // 手动校验功能
        ValidatorUtils.validate(pageQuery);
        Page page = new Page(pageQuery.getPageNo(), pageQuery.getPageSize());
        UserDO query = new UserDO();
        // TODO 如果属性不一致，需要做特殊处理
        BeanUtils.copyProperties(pageQuery.getQuery(),query);
        QueryWrapper queryWrapper =
                new QueryWrapper(query);
        // 查询
        IPage<UserDO> iPage =
                userMapper.selectPage(page,queryWrapper);
        // 结果解析
        PageResult pageResult = new PageResult();
        pageResult.setPageNo((int) iPage.getCurrent());
        pageResult.setPageSize((int) iPage.getSize());
        pageResult.setTotal(iPage.getTotal());
        pageResult.setPagNum(iPage.getPages());

        List<UserDTO> userDTOList = Optional.ofNullable(iPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDO -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDO, userDTO);
                    return userDTO;
                }).collect(Collectors.toList());
        pageResult.setData(userDTOList);
        return pageResult;
    }
}
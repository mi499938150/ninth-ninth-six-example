package com.mi.mapper;

import com.mi.domain.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc:
 */

@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void find(){
        HashMap<String,Object>  param =
                new HashMap<>();
        param.put("username","username1");
        List<UserDO> userDOList = userMapper.selectByMap(param);
        log.info("{}",userDOList);
    }
}
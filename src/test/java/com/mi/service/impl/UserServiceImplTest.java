package com.mi.service.impl;

import com.mi.domain.dto.UserDTO;
import com.mi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : Rong
 * @date : 2020/7/13
 * @Desc:
 */
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username1");
        userDTO.setPassword("password1");
        userDTO.setEmail("email@email.com");
        userDTO.setAge(1);
        userDTO.setPhone("150111111");
        userDTO.setVersion(1L);
        int save = userService.save(userDTO);
        log.info("{}",save);
    }
}
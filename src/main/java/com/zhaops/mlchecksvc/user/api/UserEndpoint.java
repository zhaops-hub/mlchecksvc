package com.zhaops.mlchecksvc.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.entity.User;
import com.zhaops.mlchecksvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SoYuan
 */
@RestController
@RequestMapping("/users")
public class UserEndpoint {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> findAll(Pageable pageable) {
        Page<User> page = this.userService.getPage(pageable);
        if (null != page) {
            return page.getContent().stream().map((user) -> {
                return new UserDto(user);
            }).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 添加用户
     * @param id
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserDto update(@RequestBody UserDto userDto){
        User user = this.userService.save(userDto);
        return (null != user) ? new UserDto(user) : null;
    }
}

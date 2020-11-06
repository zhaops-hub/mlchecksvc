package com.zhaops.mlchecksvc.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhaops.mlchecksvc.user.common.UserLoginToken;
import com.zhaops.mlchecksvc.user.dto.ResultModel;
import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.dto.UserLoginModel;
import com.zhaops.mlchecksvc.user.entity.User;
import com.zhaops.mlchecksvc.user.service.TokenService;
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

    @Autowired
    private TokenService tokenService;


    /**
     * 获取用户列表
     * @return
     */
    @UserLoginToken
    @RequestMapping(method = RequestMethod.GET)
    public ResultModel<List<UserDto>> findAll(Pageable pageable) {
        ResultModel<List<UserDto>> resultModel = new ResultModel<>();
        resultModel.setData(this.userService.getPage(pageable));
        return resultModel;
    }

    /**
     * 添加用户
     * @param id
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserDto update(@RequestBody UserDto userDto) {
        return this.userService.save(userDto);
    }


    /**
     * 登录
     * @param id
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel<UserLoginModel> login(@RequestBody UserDto userDto) {
        ResultModel<UserLoginModel> result = new ResultModel<>();
        result.setCode(0);
        UserDto user = userService.login(userDto.getUserName(), userDto.getPassword());
        if (user == null) {
            result.setCode(-1);
            result.setMsg("用户名密码错误！");
            return result;
        }
        UserLoginModel loginModel = new UserLoginModel();
        loginModel.setToken(tokenService.getToken(user));
        result.setData(loginModel);
        return result;
    }
}

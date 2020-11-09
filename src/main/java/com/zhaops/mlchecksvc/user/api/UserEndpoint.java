package com.zhaops.mlchecksvc.user.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.zhaops.mlchecksvc.user.common.UserLoginToken;
import com.zhaops.mlchecksvc.user.dto.ResultModel;
import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.dto.UserLoginModel;
import com.zhaops.mlchecksvc.user.entity.User;
import com.zhaops.mlchecksvc.user.service.TokenService;
import com.zhaops.mlchecksvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping(method = RequestMethod.GET)
    public ResultModel<List<UserDto>> findAll(@RequestParam(required = false) String userName, Integer page, Integer rows) {
        page = page == null ? 1 : page;
        rows = rows == null ? Integer.MAX_VALUE : rows;
        Pageable pageable = PageRequest.of(page - 1, rows, Sort.by(Sort.Direction.ASC, "id"));
        ResultModel<List<UserDto>> resultModel = new ResultModel<>();
        User where = new User();
        where.setUserName(userName);
        Page<User> pageUsers = this.userService.getUsers(where, pageable);
        List<UserDto> users = pageUsers.getContent().stream().map((user) -> {
            return new UserDto(user);
        }).collect(Collectors.toList());
        resultModel.setData(users);
        resultModel.setTotal(pageUsers.getTotalElements());
        resultModel.setPages(pageUsers.getTotalPages());
        return resultModel;
    }


    /**
     * 添加用户
     *
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
     *
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

        /**判断公司名称
         * 超级管理员不用做判断
         * */

        if (user.getIsAdmin() != 1 && user.getCompanyName() != userDto.getCompanyName()) {
            result.setCode(-1);
            result.setMsg("公司名称输入不正确！");
            return result;
        }

        UserLoginModel loginModel = new UserLoginModel();
        loginModel.setToken(tokenService.getToken(user));
        result.setData(loginModel);
        return result;
    }

    /**
     * 登录
     *
     * @param id
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public ResultModel<UserLoginModel> adminLogin(@RequestBody UserDto userDto) {
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

    /**
     * 通过token 获取用户信息
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public ResultModel<UserDto> userInfo(@RequestHeader("token") String token) {
        ResultModel<UserDto> result = new ResultModel<>();
        result.setCode(0);
        String userId = JWT.decode(token).getAudience().get(0);
        UserDto user = userService.load(Long.parseLong(userId));
        result.setData(user);
        return result;
    }

    /**
     * 查看用户名是否存在
     *
     * @param userName
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/existUserName/{userName}", method = RequestMethod.GET)
    public ResultModel<Boolean> existUserName(@PathVariable String userName) {
        ResultModel<Boolean> result = new ResultModel<>();
        result.setCode(0);
        Boolean isExit = this.userService.existUserName(userName) != null;
        result.setData(isExit);
        return result;
    }
}

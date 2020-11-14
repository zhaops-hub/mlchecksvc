package com.zhaops.mlchecksvc.user.service;

import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author SoYuan
 */
public interface UserService {
    /**
     * 获取商品配置的分页数据
     *
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<User> getPage(Pageable pageable);

    /**
     * 条件查询
     * @param where
     * @param pageable
     * @return
     */
    Page<User> getUsers(User where, Pageable pageable);

    /**
     * 保存/更新用户
     *
     * @param userDto
     * @return
     */
    UserDto save(UserDto userDto);

    /**
     * 保存/更新用户
     *
     * @param userDto
     * @return
     */
    UserDto update(UserDto userDto);

    /**
     * 加载指定的用户信息
     *
     * @param id 用户主键
     * @return
     */
    UserDto load(Long id);

    /**
     * 删除指定的用户
     *
     * @param id 所要删除的用户主键
     */
    void delete(Long id);

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    UserDto login(String userName, String password);

    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    UserDto existUserName(String userName);
}

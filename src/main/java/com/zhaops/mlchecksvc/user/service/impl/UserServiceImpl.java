package com.zhaops.mlchecksvc.user.service.impl;

import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.entity.User;
import com.zhaops.mlchecksvc.user.repository.UserRepository;
import com.zhaops.mlchecksvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author SoYuan
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Override
    public Page<User> getPage(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public UserDto save(@org.jetbrains.annotations.NotNull UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setCompanyName(userDto.getCompanyName());
        user.setIsDelete(0);
        user.setExpired(userDto.getExpired());
        user = this.userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public UserDto load(Long id) {
        User user = this.userRepository.findById(id).get();
        return new UserDto(user);
    }

    @Override
    public void delete(Long id) {
        User user = this.userRepository.getOne(id);
        user.setIsDelete(0);
        this.userRepository.save(user);
    }

    @Override
    public UserDto login(String userName, String password) {
        User user = this.userRepository.login(userName, password);

        /**修改最后登录时间 */
        user.setLastLoginTime(new Date());
        user = this.userRepository.save(user);

        return user != null ? new UserDto(user) : null;
    }


}

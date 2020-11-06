package com.zhaops.mlchecksvc.user.service.impl;

import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.entity.User;
import com.zhaops.mlchecksvc.user.repository.UserRepository;
import com.zhaops.mlchecksvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public User save(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setCompanyName(userDto.getCompanyName());
        user.setIsDelete(0);
        user.setExpired(userDto.getExpired());
        return this.userRepository.save(user);
    }

    @Override
    public User load(Long id) {
        return this
                .userRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        User user = this.userRepository.getOne(id);
        user.setIsDelete(0);
        this.userRepository.save(user);
    }


}

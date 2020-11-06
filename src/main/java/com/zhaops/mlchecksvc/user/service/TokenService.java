package com.zhaops.mlchecksvc.user.service;

import com.zhaops.mlchecksvc.user.dto.UserDto;

/**
 * @author SoYuan
 */
public interface TokenService {
    String getToken(UserDto user);
}

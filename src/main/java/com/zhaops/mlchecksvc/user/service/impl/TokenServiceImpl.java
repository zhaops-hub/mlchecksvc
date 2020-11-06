package com.zhaops.mlchecksvc.user.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * @author SoYuan
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(UserDto user) {
        String token = JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }
}

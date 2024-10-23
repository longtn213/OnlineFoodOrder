package com.DragonSouth.service;

import com.DragonSouth.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User findUserByJwtToken(String jwtToken);
    public User findUserByEmail(String email);
}

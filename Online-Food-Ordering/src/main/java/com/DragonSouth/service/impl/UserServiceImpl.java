package com.DragonSouth.service.impl;

import com.DragonSouth.configure.JwtProvider;
import com.DragonSouth.model.User;
import com.DragonSouth.repository.UserRepository;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwtToken) {
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);

        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("User not found");
        }

        return user;
    }
}

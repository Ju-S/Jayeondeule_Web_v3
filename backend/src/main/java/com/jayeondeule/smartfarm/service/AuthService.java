package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.auth.LoginDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.entity.user.User;
import com.jayeondeule.smartfarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //로그인
    public UserDTO login(LoginDTO loginInfo) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        User find = userRepository.findByUserId(loginInfo.getUserId());

        if(find == null) {
            throw new RuntimeException("user not found");
        }

        if(!passwordEncoder.matches(loginInfo.getPasswd(), find.getPasswd())){
            throw new RuntimeException("Invalid password");
        }

        return mapper.convertValue(find, UserDTO.class);
    }
}

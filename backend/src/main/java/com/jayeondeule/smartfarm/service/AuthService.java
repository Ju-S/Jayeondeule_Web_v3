package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.auth.LoginDTO;
import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
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
    public UserClaimDTO login(LoginDTO loginInfo) {
        User find = userRepository.findByUserId(loginInfo.getUserId());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        if(find == null) {
            throw new RuntimeException("아이디와 일치하는 회원이 없습니다.");
        }

        if(!passwordEncoder.matches(loginInfo.getPasswd(), find.getPasswd())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return mapper.convertValue(find, UserClaimDTO.class);
    }
}

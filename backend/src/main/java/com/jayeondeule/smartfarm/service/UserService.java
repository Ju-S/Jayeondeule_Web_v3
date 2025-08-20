package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeondeule.smartfarm.dto.auth.LoginDTO;
import com.jayeondeule.smartfarm.dto.user.UserPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserInsertDTO;
import com.jayeondeule.smartfarm.entity.user.User;
import com.jayeondeule.smartfarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    //사용자 비즈니스 로직 및 인증 처리

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public boolean signup(UserInsertDTO signupInfo) {
        String hashed = passwordEncoder.encode(signupInfo.getPasswd());
        signupInfo.setPasswd(hashed);

        ObjectMapper mapper = new ObjectMapper();
        userRepository.save(mapper.convertValue(signupInfo, User.class));

        return true;
    }

    //로그인
    public UserPatchDTO login(LoginDTO loginInfo) {

        //TODO: loginInfo에 맞는 정보가 있는 지 조회

        return null;
    }

    //아이디로 유저 정보 조회
    public boolean idDuplicationCheck(String idInfo) {

        //TODO: idInfo에 들어온 정보와 겹치는 user_id가 존재하는지 조회

        return true;
    }
}

package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeondeule.smartfarm.dto.auth.LoginDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.dto.user.UserPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserInsertDTO;
import com.jayeondeule.smartfarm.entity.user.User;
import com.jayeondeule.smartfarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    //사용자 비즈니스 로직 및 인증 처리

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public boolean insertUser(UserInsertDTO signupInfo) {
        String hashed = passwordEncoder.encode(signupInfo.getPasswd());
        signupInfo.setPasswd(hashed);

        ObjectMapper mapper = new ObjectMapper();
        userRepository.save(mapper.convertValue(signupInfo, User.class));

        return true;
    }

    //아이디 중복 확인
    public boolean idDuplicationCheck(String idInfo) {
        return userRepository.existsByUserId(idInfo);
    }

    public List<UserDTO> getUsers() {
        ObjectMapper mapper = new ObjectMapper();

        List<UserDTO> result = userRepository.findAll().stream()
                .map(user -> mapper.convertValue(user, UserDTO.class))
                .toList();

        return result;
    }
}

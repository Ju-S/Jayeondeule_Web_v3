package com.jayeondeule.smartfarm.service;

import com.jayeondeule.smartfarm.dto.IdDuplicationCheckDTO;
import com.jayeondeule.smartfarm.dto.LoginDTO;
import com.jayeondeule.smartfarm.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class UserService {
    //사용자 비즈니스 로직 및 인증 처리

    //회원가입
    public UserDTO signup(UserDTO signupInfo) {

        //TODO: signupInfo에 따라 유저 등록

        return UserDTO;
    }

    //로그인
    public UserDTO login(LoginDTO loginInfo) {

        //TODO: loginInfo에 맞는 정보가 있는 지 조회

        return UserDTO;
    }

    //아이디 중복 확인
    public IdDuplicationCheckDTO idDuplicationCheck(IdDuplicationCheckDTO idInfo) {

        //TODO: idInfo에 들어온 정보와 겹치는 user_id가 존재하는지 조회

        return IdDuplicationCheckDTO;
    }
}

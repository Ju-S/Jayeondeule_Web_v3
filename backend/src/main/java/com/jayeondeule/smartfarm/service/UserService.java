package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeondeule.smartfarm.dto.auth.LoginDTO;
import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsDTO;
import com.jayeondeule.smartfarm.dto.user.*;
import com.jayeondeule.smartfarm.entity.memo.FarmHouseCrops;
import com.jayeondeule.smartfarm.entity.user.User;
import com.jayeondeule.smartfarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final ObjectMapper mapper;

    //회원가입
    public boolean insertUser(UserInsertDTO signupInfo) {
        String hashed = passwordEncoder.encode(signupInfo.getPasswd());
        signupInfo.setPasswd(hashed);

        userRepository.save(mapper.convertValue(signupInfo, User.class));

        return true;
    }

    //아이디 중복 확인
    public boolean idDuplicationCheck(String idInfo) {
        return userRepository.existsByUserId(idInfo);
    }

    //권한부여를 위한 유저 리스트 조회(관리자용)
    public Page<UserDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("rgstDttm").descending());

        Page<User> userList = userRepository.findAll(pageable);

        return userList.map(user -> mapper.convertValue(user, UserDTO.class));
    }

    //권한부여를 위한 기존 유저 리스트 조회(관리자용)
    public Page<UserDTO> getUsers(int page, int size, long searchQuery) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("rgstDttm").descending());

        Page<User> userList = userRepository.findAllByFarmId(searchQuery, pageable);

        return userList.map(user -> mapper.convertValue(user, UserDTO.class));
    }

    //사용자 정보 조회
    public UserDTO getUser(String userId) {
        return mapper.convertValue(userRepository.findByUserId(userId), UserDTO.class);
    }

    //비밀번호 변경
    public boolean patchUserPassword(UserPasswordPatchDTO passwordInfo, String userId) {
        User target = userRepository.findByUserId(userId);

        if(passwordEncoder.matches(passwordInfo.getOldPassword(), target.getPasswd())) {
            String hashedNew = passwordEncoder.encode(passwordInfo.getNewPassword());
            target.changePassword(hashedNew);
            userRepository.save(target);
            return true;
        }

        return false;
    }

    //사용자 정보 수정
    public void patchUser(UserPatchDTO modifiedInfo, String userId) {
        User target = userRepository.findByUserId(userId);

        target.changeUserName(modifiedInfo.getUserName());
        target.changeUserPstn(modifiedInfo.getPstn());
        target.changeUserHpNo(modifiedInfo.getHpNo());

        userRepository.save(target);
    }

    //사용자에게 농장 접근 권한 부여
    public void patchUserFarmId(UserFarmIdPatchDTO modifiedInfo, String userId) {
        User target = userRepository.findByUserId(userId);

        target.setFarmId(modifiedInfo.getFarmId());

        userRepository.save(target);
    }

    //사용자에게 부여된 농장ID 조회
    public long getUserOwnedFarmId(String userId) {
        return userRepository.findByUserId(userId).getFarmId();
    }
}

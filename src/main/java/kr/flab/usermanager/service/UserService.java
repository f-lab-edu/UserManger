package kr.flab.usermanager.service;


import kr.flab.usermanager.domain.User;
import kr.flab.usermanager.dto.UserDTO;
import kr.flab.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public Long joinUser(User user) {
        // 멤버가 있는지 찾기
        User findUser = userRepository.findByUserId(user.getUserId());
        if(findUser == null){
            return userRepository.saveUser(user);
        } else { // 이미 존재하는 유저
            throw new RuntimeException("이미 아이디가 존재합니다.");
        }



    }
    // 이름으로 회원 조회
    public List<UserDTO> findUserByName(String name) {
        return userRepository.findByUserName(name);
    }
    // 전체 회원 조회
    public List<UserDTO> findAllUser() {
        return userRepository.findAll();
    }
}

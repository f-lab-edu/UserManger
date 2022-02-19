package kr.flab.usermanager.service;

import kr.flab.usermanager.domain.User;
import kr.flab.usermanager.dto.UserDto;
import kr.flab.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    // service layer... Entity 가 서비스 메서드의 파라미터로 직접 사용되는 케이스는 적은게 좋다

    // 회원가입
    @Transactional
    public Long joinUser(User user) {  // Command, Criteria
        User findUser = userRepository.findByUserId(user.getUserId());
        if (findUser != null) {
            throw new RuntimeException("이미 아이디가 존재합니다.");
        }
        return userRepository.saveUser(user);
    }

    // 이름으로 회원 조회
    public List<UserDto> findUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    // 전체 회원 조회
    public List<UserDto> findAllUser() {
        return userRepository.findAll();
    }
}

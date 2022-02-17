package kr.flab.usermanager.repository;

import kr.flab.usermanager.domain.User;
import kr.flab.usermanager.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository {

    // 유저를 저장하고 id 반환
    public Long saveUser(User user);

    // 유저 PK로 유저를 찾아 반환
    public User findById(Long id);

    // 유저 아이디로 유저를 찾아 반환
    public User findByUserId(String userId);

    // 유저 이름으로 유저를 찾아 반환
    public List<UserDTO> findByUserName(String name);

    // 유저 전체를 불러오기
    public List<UserDTO> findAll();



}

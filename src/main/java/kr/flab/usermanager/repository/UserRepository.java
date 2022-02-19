package kr.flab.usermanager.repository;

import kr.flab.usermanager.domain.User;
import kr.flab.usermanager.dto.UserDto;

import java.util.List;

public interface UserRepository {

    Long saveUser(User user);

    User findById(Long id);

    User findByUserId(String userId);

    List<UserDto> findByUserName(String name);

    List<UserDto> findAll();
}

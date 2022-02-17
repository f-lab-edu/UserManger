package kr.flab.usermanager.contoller;

import kr.flab.usermanager.domain.User;
import kr.flab.usermanager.dto.ErrorResult;
import kr.flab.usermanager.dto.JoinUserDTO;
import kr.flab.usermanager.dto.UserDTO;
import kr.flab.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {

        return new ErrorResult("400", e.getMessage());
    }


    // 회원가입
    @PostMapping("/usermanager/user")
    public void join(@RequestBody JoinUserDTO userDTO){
        User joinUser = User.builder().userId(userDTO.getUserId())
                .gender(userDTO.getGender())
                .name(userDTO.getName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        userService.joinUser(joinUser);

    }
    // 유저 전체 조회
    @GetMapping("/usermanager/users")
    public List<UserDTO> retrieveAllUser(){
        return userService.findAllUser();
    }

    // 유저 한명 이름으로 조회
    @GetMapping("/usermanager/user")
    public List<UserDTO> retrieveUserByName(@RequestParam String name) {
        return userService.findUserByName(name);
    }
}

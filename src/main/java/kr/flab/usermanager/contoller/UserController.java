package kr.flab.usermanager.contoller;

import kr.flab.usermanager.domain.User;
import kr.flab.usermanager.dto.JoinUserDto;
import kr.flab.usermanager.dto.UserDto;
import kr.flab.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping
    public Long join(@RequestBody @Valid JoinUserDto userDto) {
//        new JoinUserDTO();  // 외부 요청이 올 때마다... 톰캣의 쓰레드 하나 하나마다,
        // userDto -> User convert... DB 에 저장한건 아니고.. 메모리상에서 convert
        // 추상화 레벨
        var password = passwordEncoder.encode(userDto.getPassword());
        User initUser = userDto.convert(password);  // 데이터베이스에 저장된 유저일까? 저장 안된 대기 상태의 유저일까?
        return userService.joinUser(initUser);
    }

    // Controller 에 일일이 구현한다고 하면 어떤 문제가 있을까?
    // 1. 중복 코드... 정책
    //      <-- 구글 http status 200, 300, 400, 500 401, 402
    //      <-- 페이스북 http status 200, body 안에서 result: success, fail, status 500 <-- 서버 에러
    // 2. 누락이 생김...


    // option + command + L

    // 유저 전체 조회
    @GetMapping
    public List<UserDto> retrieveAllUser() {
        return userService.findAllUser();
    }

    // 유저 한명 이름으로 조회
    @GetMapping()
    public List<UserDto> retrieveUserByName(@RequestParam String name) {
        return userService.findUserByName(name);
    }

//    // 유저 한명 이름으로 조회
//    @GetMapping("/{userId}")
//    public List<UserDto> retrieveUserByName(@PathVariable Long userId) {
//        return userService.findUserByName(userId);
//    }
}

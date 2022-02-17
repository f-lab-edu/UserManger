package kr.flab.usermanager.dto;

import kr.flab.usermanager.domain.Gender;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class JoinUserDTO {

    private String name; // 이름

    private String userId; // 유저 아이디

    private String password; // 비밀번호

    private Gender gender; // 성별



}

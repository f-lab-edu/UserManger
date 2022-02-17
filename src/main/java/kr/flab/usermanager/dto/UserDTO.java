package kr.flab.usermanager.dto;

import kr.flab.usermanager.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class UserDTO {
    private Long id;

    private String name;

    private Gender gender;

    private LocalDateTime joinDate;

}

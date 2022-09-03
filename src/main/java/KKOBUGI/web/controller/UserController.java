package KKOBUGI.web.controller;


import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.exception.DuplicateUserException;
import KKOBUGI.web.exception.FindNoUserException;
import KKOBUGI.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static KKOBUGI.web.domain.dto.UserDto.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 : 유저 생성
     */
    @PostMapping("/users")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest u) {
        // 회원 가입 시 loginID, nickname 증복 검증
        userService.duplicateCheck(u.getLoginId(), u.getNickname());

        User user = new User(u.getLoginId(), u.getPw(), u.getNickname());
        Long id = userService.save(user).getId();
        return new CreateUserResponse(id);
    }


    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResLoginDto Login(@RequestBody ReqLoginDto req) {
        String loginId = req.getLoginId();
        String pw = req.getPw();

        userService.login(loginId, pw);
        /* 로그인 완료*/

        User user = userService.findByloginId(loginId);
        return new ResLoginDto(user.getId(), user.getNickname());
    }
}

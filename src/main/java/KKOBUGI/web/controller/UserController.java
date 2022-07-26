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
     * */
    @PostMapping("/users")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest u){
        // 회원 가입 시 loginID, nickname 증복 검증
        userService.duplicateCheck(u.getLoginId(), u.getNickname());

        User user = new User(u.getLoginId(),u.getPw(),u.getNickname());
        Long id = userService.save(user);
        return new CreateUserResponse(id);
    }


    /**
     * 로그인 */
    @PostMapping("/login")
    public ResLoginDto Login(@RequestBody ReqLoginDto req) {
        String loginId = req.getLoginId();
        String pw = req.getPw();

        userService.login(loginId,pw);
        /* 로그인 완료*/

        User user = userService.findByloginId(loginId);
        return new ResLoginDto(user.getId(), user.getNickname());
    }


    /**
     * 유저 전체 조회
     **/
    @GetMapping("/users")
    public Result Users(){
        List<User> findUsers = userService.findUsers();
        List<UserDtos> users = new ArrayList<>();

        for (User u : findUsers) {
            users.add(new UserDtos
                    (u.getId(), u.getLogin_Id(),u.getLogin_Pw(),u.getNickname())
            );
        }
        return new Result(users.size(), users);
    }

    /**
     * 유저 수정
     * */
    @PatchMapping("/users")
    public UpdateUserResponse updateUser(@RequestBody @Valid UpdateUserRequest req){

        userService.update(req.getUserId(), req.getNickname());
        User findUser = userService.findOne(req.getUserId());
        return new UpdateUserResponse(findUser.getId(), findUser.getNickname());
    }


    /**
     * 회원탈퇴 : 유저 삭제
     * */
    @DeleteMapping("/users")
    public DeleteUserRes deleteUser(@RequestBody @Valid DeleteUserReq req){
        String loginId = req.getLoginId();
        String pw = req.getPw();
        userService.login(loginId, pw);
        User user = userService.findByloginId(loginId);
        Long deletedId = user.getId();
        userService.delete(user);
        return new DeleteUserRes(deletedId);
    }

}

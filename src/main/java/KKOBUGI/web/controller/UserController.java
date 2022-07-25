package KKOBUGI.web.controller;


import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.exception.DuplicateUserException;
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
        DuplicateCheck duplicateCheck = userService.duplicateCheck(u.getLoginId(), u.getNickname());
        say(duplicateCheck);

        User user = new User(u.getLoginId(),u.getPw(),u.getNickname());
        Long id = userService.save(user);
        return new CreateUserResponse(id);
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
    @PatchMapping("/users/{id}")
    public UpdateUserResponse updateUser(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateUserRequest request){
        userService.update(id, request.getNickname());
        User findUser = userService.findOne(id);
        return new UpdateUserResponse(findUser.getId(), findUser.getNickname());
    }


    /**
     * 회원탈퇴 : 유저 삭제
     * */
    @DeleteMapping("/users")
    public DeleteUserRes deleteUser(@RequestBody DeleteUserReq req){
        Boolean check = userService.check(req.getLoginId(), req.getPw());
        User user = userService.findByloginId(req.getLoginId());
        Long id = userService.delete(user);
        return new DeleteUserRes(id);
    }



    private static void say(DuplicateCheck d){
        if(d.getB1()==false) throw new DuplicateUserException("로그인 아이디가 이미 존재합니다. 다시 설정해주세요");
        if(d.getB2()==false) throw new DuplicateUserException("유저 닉네임이 이미 존재합니다. 다시 설정해주세요.");
    }


}

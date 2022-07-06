package KKOBUGI.web.controller;

import KKOBUGI.web.domain.User;
import KKOBUGI.web.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     * 유저 생성 : userId
     * */
    @PostMapping("/api/users")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest u){
        User user = new User();
        user.setNickname(u.getNickname());

        Long id = userService.save(user);
        return new CreateUserResponse(id);
    }

    /**
     * 유저 전체 조회
     * */
    @GetMapping("/api/users")
    public Result Users(){
        List<User> findUsers = userService.findUsers();
        List<UserDto> users = new ArrayList<>();

        for (User u : findUsers) {
            users.add(new UserDto
                    (u.getId(), u.getLogin_Id(),u.getLogin_Pw(),u.getNickname())
            );
        }
        return new Result(users.size(), users);
    }

    /**
     * 유저 수정
     * */
    @PutMapping("/api/users/{id}")
    public UpdateUserResponse updateUser(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateUserRequest request){
        userService.update(id, request.getNickname());
        User findUser = userService.findOne(id);
        return new UpdateUserResponse(findUser.getId(), findUser.getNickname());
    }


    /**
     * 여기서부터 dto 입니다!!
     */

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id){
            this.id = id;
        }
    }

    @Data
    static class CreateUserRequest {
        @NotEmpty
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    static class UserDto {
        private Long id;
        private String login_Id;
        private String login_Pw;
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class UpdateUserRequest{
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class UpdateUserResponse {
        private Long id;
        private String nickname;
    }
}

package KKOBUGI.web.controller;

import KKOBUGI.web.domain.User;
import KKOBUGI.web.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request){
        User user = new User();
        user.setId(request.getId());

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
    @PatchMapping("/api/users/{id}")
    public UpdateUserResponse updateUser(
            @RequestBody UpdateUserRequest request,
            @PathVariable("id") Long id){
        userService.update(id, request.getNickname());
        User updatedUser = userService.findOne(id);
        return new UpdateUserResponse(updatedUser.getId(), updatedUser.getNickname());
    }


    /**
     * 여기서부터 dto 입니다!!
     */

    @Data
    @AllArgsConstructor
    public static class CreateUserResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    public static class CreateUserRequest {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    public static class UserDto {
        private Long id;
        private String login_Id;
        private String login_Pw;
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    public static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    public static class UpdateUserRequest{
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    private static class UpdateUserResponse {
        private Long id;
        private String nickname;
    }
}

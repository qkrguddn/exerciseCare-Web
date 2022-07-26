package KKOBUGI.web.domain.dto;

import KKOBUGI.web.exception.DuplicateUserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {
    @Data
    @AllArgsConstructor
    public static class CreateUserResponse {
        // userId 반환
        private Long id;
    }

    @Data
    public static class CreateUserRequest {
        @NotEmpty
        private String loginId;
        @NotEmpty
        private String pw;
        @NotEmpty
        private String nickname;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReqLoginDto{
        @NotNull
        private String loginId;
        @NotNull
        private String pw;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResLoginDto{
        private Long userId;
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    public static class UserDtos {
        private Long id;
        private String login_Id;
        private String login_Pw;
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteUserReq{
        @NotEmpty @NotNull
        private String loginId;
        @NotNull @NotEmpty @NotBlank
        private String pw;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteUserRes{
        private Long userId;
    }

    @Data
    @AllArgsConstructor
    public static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DuplicateCheck{
        private Boolean b1;
        private Boolean b2;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginIdCheck{
        private Boolean b;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateUserRequest{
        private Long userId;
        @NotEmpty
        @NotNull @NotBlank
        private String nickname;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateUserResponse {
        private Long id;
        private String nickname;
    }
}

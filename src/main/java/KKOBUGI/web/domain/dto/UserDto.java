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
    public static class ReqLoginDto {
        @NotNull
        private String loginId;
        @NotNull
        private String pw;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResLoginDto {
        private Long userId;
        private String nickname;
    }
}

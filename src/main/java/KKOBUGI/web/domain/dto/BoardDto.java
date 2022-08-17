package KKOBUGI.web.domain.dto;

import KKOBUGI.web.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    @Data
    public static class BoardRequestDto{
        private Long userId;
        @NotEmpty @NotNull @NotBlank
        private String title;
        @NotEmpty @NotNull @NotBlank
        private String content;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardReqFixDto{
        @NotNull @NotEmpty @NotBlank
        public String title;
        @NotNull @NotEmpty @NotBlank
        public String content;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseBoardDto {
        public Long id;
        public String title;
        public String content;
        public LocalDateTime createDate;
        public LocalDateTime modifyDate;
        public UserDto user;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto{
        public Long id;
        public String login_Id;
        public String login_Pw;
        public String nickname;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseBoardDetail{
        ResponseBoardDto boardDto;
        List<Comment> comments;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentDto{
        public Long id;
        public String nickname;
        public String content;
        public LocalDateTime createDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestBoard{
        @NotNull
        public Long boardId;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardDetailDto{
        private Long id;
        private Long userId;
        private String nickname;
        private String title;
        private String content;
        private List<CommentDtos> commentDtosList = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentDtos{
        private Long id;
        private Long userId;
        private String nickname;
        private String content;
    }
}

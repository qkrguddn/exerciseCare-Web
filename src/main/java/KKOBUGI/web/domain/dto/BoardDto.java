package KKOBUGI.web.domain.dto;

import KKOBUGI.web.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardRequestDto{
        public Long userId;
        public String title;
        public String content;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardReqFixDto{
        public String title;
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
        public Long boardId;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardDetailDto{
        private Long id;
        private String title;
        private String content;
        private List<CommentDtos> commentDtosList = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentDtos{
        private Long id;
        private String content;
    }
}

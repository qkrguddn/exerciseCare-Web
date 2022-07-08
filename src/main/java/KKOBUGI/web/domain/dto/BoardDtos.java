package KKOBUGI.web.domain.dto;

import KKOBUGI.web.controller.BoardController;
import KKOBUGI.web.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDtos {

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
    public static class BoardDto{
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
        BoardDto boardDto;
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
}

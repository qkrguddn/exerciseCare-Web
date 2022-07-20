package KKOBUGI.web.domain.dto;


import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReqCreateDto{
        public Long userId;
        public String content;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResCreateDto {
        public Long commentId;
        public String content;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResDeleteDto{
        public Long id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReqFixDto{
        public String content;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResFixDto{
        public Long id;
    }

}
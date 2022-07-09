package KKOBUGI.web.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommentDto {

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class CommentFixReqDto{
        private Long id; // Comment id
        private String content;
    }
}

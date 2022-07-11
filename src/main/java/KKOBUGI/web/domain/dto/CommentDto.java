package KKOBUGI.web.domain.dto;


import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long postId;
    private String nickname;
    private String content;
    private LocalDateTime createDate;
    private Long number;

    public CommentDto() {
    }
    /*dto -> entity */
    public Comment toEntity() {
        return Comment.builder()
                .postId(postId)
                .nickname(nickname)
                .content(content)
                .createDate(createDate.now())
                .build();
    }

    public CommentDto(Long postId, String nickname, String content, LocalDateTime createDate) {
        this.postId = postId;
        this.nickname = nickname;
        this.content = content;
        this.createDate = createDate;
    }

    @Builder /* entity -> dto */
    public CommentDto(Long postId,Long number, String nickname, String content, LocalDateTime createDate) {
        this.postId = postId;
        this.number = number;
        this.nickname = nickname;
        this.content = content;
        this.createDate = LocalDateTime.now();
    }
}
//dto에도 id넣어야 하나??
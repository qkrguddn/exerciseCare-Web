package KKOBUGI.web.domain.entity;

import KKOBUGI.web.domain.dto.CommentDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;
    private String nickname;
    private String content;
    private Long postId;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(Board board) {

    }
    public void changeComment(String content){
        this.content = content;
    }

    public CommentDto toCommentDto() {
        return CommentDto.builder()
                .postId(postId)
                .nickname(nickname)
                .content(content)
                .createDate(createDate.now())
                .build();
    }
    @Builder // 빌더 패턴 적용
    public Comment(Long id, Long postId, String nickname, String content, LocalDateTime createDate) {
        this.id = id;
        this.postId = postId;
        this.nickname = nickname;
        this.content = content;
        this.createDate = createDate.now();
    }
}
package KKOBUGI.web.domain.entity;

import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
//@MappedSuperclass   @EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Comment(String content) {
        //이것도 수정 코드 짤 때 한 건가 확인필요
        this.content = content;
    }
    public Comment(CommentDto commentDto) {
        //수정할 때 만든 건데 다시 짜야함.
        this.nickname = commentDto.getNickname();
        this.content  = commentDto.getContent();

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
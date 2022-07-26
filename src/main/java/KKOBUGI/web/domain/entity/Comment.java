package KKOBUGI.web.domain.entity;

import KKOBUGI.web.domain.dto.CommentDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @NotNull
    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(){}

    public void setComment(String content, LocalDateTime createDate, User user, Board board){
        this.content = content;
        this.createDate = createDate;
        this.user = user;
        this.board = board;
    }

    public void fixComment(String content){
        this.content = content;
    }

}
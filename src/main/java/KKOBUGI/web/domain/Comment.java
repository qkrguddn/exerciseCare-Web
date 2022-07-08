package KKOBUGI.web.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    @NotNull
    private Long id;

    private String content;
    private LocalDateTime createDate;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(){}

    public Comment(String content, LocalDateTime createDate, Board board, User user) {
        this.content = content;
        this.createDate = createDate;
        this.board = board;
        this.user = user;
    }
}

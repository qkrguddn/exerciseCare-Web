package KKOBUGI.web.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Slf4j @Getter @Setter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    @NotNull
    private Long id;

    private String nickname;

    private String content; /// 보류 ///

    private LocalDateTime createDate;

    /*FK*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    /*FK*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

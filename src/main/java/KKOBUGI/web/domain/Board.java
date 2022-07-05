package KKOBUGI.web.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Slf4j
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id") @NotNull
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(){}
}

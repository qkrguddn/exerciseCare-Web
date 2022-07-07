package KKOBUGI.web.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    @NotNull
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();

    public Board(){}

    public Board(String title, String content, LocalDateTime createDate, LocalDateTime modifyDate, User user) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.user = user;
    }
}

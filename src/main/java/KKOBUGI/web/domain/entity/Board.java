package KKOBUGI.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    @NotNull
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public void eraseComment(Comment comment){
        comments.remove(comment);
    }

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

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.user = user;
    }

    public void setBoard(String title, String content){
        this.title = title;
        this.content = content;
        this.modifyDate = LocalDateTime.now();
    }
}

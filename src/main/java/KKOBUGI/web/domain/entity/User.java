package KKOBUGI.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@JsonAutoDetect
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    @NotNull
    private Long id;

    @NotNull
    private String login_Id;
    private String login_Pw;
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private List<ExerciseLog> exerciseLogs = new ArrayList<>();

    public User(){}

    public User(String login_Id, String login_Pw, String nickname) {
        this.login_Id = login_Id;
        this.login_Pw = login_Pw;
        this.nickname = nickname;
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public void fixNickname(String nickname){
        this.nickname=nickname;
    }

    public void saveBoard(Board board){
        boards.add(board);
    }

    public void saveComment(Comment comment){
        comments.add(comment);
    }

//    public void saveExerciseLog(ExerciseLog exerciseLog){
//        exerciseLogs.add(exerciseLog);
//    }
}

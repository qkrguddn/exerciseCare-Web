package KKOBUGI.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    @NotNull
    private Long id;

    @NotNull
    private String loginId;
    private String loginPw;
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    public User(String loginId, String loginPw, String nickname) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.nickname = nickname;
    }

    public User(String nickname) {
        this.nickname = nickname;
    }


    public void saveBoard(Board board){
        boards.add(board);
    }

}

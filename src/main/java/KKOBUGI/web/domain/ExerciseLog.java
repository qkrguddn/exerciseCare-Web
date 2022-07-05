package KKOBUGI.web.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ExerciseLog {

    @Id @GeneratedValue
    @NotNull
    @Column(name = "exerciselog_id")
    private Long id;

    private String content;
    private int time;

    /*FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public ExerciseLog(){}
}

package KKOBUGI.web.domain.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ExerciseLog {

    @Id @GeneratedValue
    @Column(name = "exerciselog_id")
    private Long id;

    private String content;
    private int time;
    private Long month;
    private Long day;
    private String date;

    /*FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
//    calendar


}

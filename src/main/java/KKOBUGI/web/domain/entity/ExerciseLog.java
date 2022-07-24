package KKOBUGI.web.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exerciseLog_id")
    private Long id;

    private String content;
    private String detailLog; //무게 or 시간(달리기)
    private Long number; //횟수
    private String time; //하루 총 운동시간
    private int date;

    /*FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public void changeExerciseLog(String content, String detailLog, Long number) {
        this.content = content;
        this.detailLog = detailLog;
        this.number = number;
    }

    public ExerciseLog(String content, String detailLog, Long number, int date, String time, User user) {
        this.content = content;
        this.detailLog = detailLog;
        this.number = number;
        this.date = date;
        this.time =time;
        this.user = user;
    }
}

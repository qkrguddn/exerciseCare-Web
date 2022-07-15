package KKOBUGI.web.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ExerciseLog {

   @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exerciselog_id")
    private Long id;

    private String content;
    private Long number;
    private String detailLog; //무게 or 시간(달리기)
    private int time; //하루 총 운동시간
    private Long month;
    private Long day;
    private String date;

    /*FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
//    calendar

    public void changeExerciseLog(String content, String detailLog, Long number){
        this.content = content;
        this.detailLog = detailLog;
        this.number = number;
    }

    public ExerciseLog(String content,String detailLog, Long number, Long month, String date) {
        this.content = content;
        this.detailLog = detailLog;
        this.number = number;
        this.month = month;
        this.date = date;
    }
}

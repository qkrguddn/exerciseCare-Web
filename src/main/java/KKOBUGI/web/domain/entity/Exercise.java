package KKOBUGI.web.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Exercise {

    @Id @GeneratedValue
    @Column(name = "exerciseIid")
    private Long id;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exerciselog_id")
    private ExerciseLog exerciseLog;

    private String exName; // 운동 이름
    private int weight; // 무게
    private int count; // 횟수

    public void setExercise(String exName, int weight, int count) {
        this.exName = exName;
        this.weight = weight;
        this.count = count;
    }

    public void setExerciseLog(ExerciseLog exerciseLog){
        this.exerciseLog = exerciseLog;
        exerciseLog.getExerciseList().add(this);
    }

    public Exercise() {
    }
}
package KKOBUGI.web.domain.entity;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ExerciseLog {

    @Id @GeneratedValue
    @NotNull
    @Column(name = "exerciselog_id")
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    /*FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "exerciseLog")
    private List<Exercise> exerciseList = new ArrayList<>();

    public ExerciseLog(){}

    public void fixExerciseLog(ExerciseLogDto.ReqDtoList req){
        exerciseList.clear();
        List<ExerciseLogDto.ReqDto> reqDtoList = req.getReqDtoList();
        for (ExerciseLogDto.ReqDto r : reqDtoList) {
            Exercise ex = new Exercise();
            ex.setExercise(r.getExName(),r.getWeight(),r.getCount());
            exerciseList.add(ex);
        }
    }

}

package KKOBUGI.web.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ExerciseLogDto {
    private Long exerciseLogId;
    private String content;
    private String detailLog; //무게 or 시간(달리기)
    private Long number;
    private int date;
    private String time;

    @Builder
    public ExerciseLogDto(Long exerciseLogId, String content, String detailLog,
                          Long number, int date, String time) {
        this.exerciseLogId = exerciseLogId;
        this.content = content;
        this.detailLog = detailLog;
        this.number = number;
        this.date = date;
        this.time = time;
    }

}

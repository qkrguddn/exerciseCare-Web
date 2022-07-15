package KKOBUGI.web.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ExerciseLogDto {
    private Long exerciseLogId;
    private String content;
    private String detailLog; //무게 or 시간(달리기)
    private Long number;
    private Long month;
    private Long day;
    private String date = month+"/"+day;

    @Builder
    public ExerciseLogDto(Long exerciseLogId, String content, String detailLog, Long number, Long month, Long day, String date) {
        this.exerciseLogId = exerciseLogId;
        this.content = content;
        this.detailLog = detailLog;
        this.number = number;
        this.month = month;
        this.day = day;
        this.date = date;
    }
}

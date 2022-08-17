package KKOBUGI.web.domain.dto;

import KKOBUGI.web.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class ExerciseLogDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExerciseLogListDto{

        public Long userId;
        public String time;
        public List<InnerData> list;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InnerData{
        public String content;
        public String detailLog; //무게 or 시간(달리기)
        public Long number;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExerciseLogRequestDto{
        public Long userId;
        public String content;
        public String detailLog; //무게 or 시간(달리기)
        public Long number;
        public int date;
        public String time;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExerciseLogResponseDto{
        public Long exerciseLogId;
        public String content;
        public String detailLog; //무게 or 시간(달리기)
        public Long number;
        public int date;
        public String time;
        public UserDto user;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto{
        public Long id;
        public String login_Id;
        public String login_Pw;
        public String nickname;
    }


  /*  @Builder
    public ExerciseLogDto(Long exerciseLogId, String content, String detailLog,
                          Long number, int date, String time) {
        this.exerciseLogId = exerciseLogId;
        this.content = content;
        this.detailLog = detailLog;
        this.number = number;
        this.date = date;
        this.time = time;
    }*/

}
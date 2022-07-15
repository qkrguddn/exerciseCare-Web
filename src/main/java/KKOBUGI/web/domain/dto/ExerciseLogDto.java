package KKOBUGI.web.domain.dto;

import KKOBUGI.web.controller.ExerciseLogController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ExerciseLogDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReqDto {
        private String exName;
        private int weight;
        private int count;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReqDtoList {
        private List<ReqDto> reqDtoList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResGetDto{
        private List<ReqDto> reqDtoList;
    }
}

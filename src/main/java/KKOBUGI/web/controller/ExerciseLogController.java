package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.Exercise;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.service.ExerciseLogService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExerciseLogController {

    private final ExerciseLogService exerciseLogService;

    /**
     * ExerciseLog 저장
     * */
    @PostMapping("/{id}") // id는 exLog 의 id
    public resDto saveExLog(
            @PathVariable("id") Long id, @RequestBody ExerciseLogDto.ReqDtoList req) {
        ExerciseLog exerciseLog = exerciseLogService.find(id);
        List<ExerciseLogDto.ReqDto> requestExLogDtoList = req.getReqDtoList();
        for (ExerciseLogDto.ReqDto r : requestExLogDtoList) {
            Exercise exercise = new Exercise();
            exercise.setExercise(r.getExName(),r.getWeight(),r.getCount());
            exercise.setExerciseLog(exerciseLog);
        }
        return new resDto(id);
    }


    /**
     * ExerciseLog 조회
     * */
    @GetMapping("/{id}")
    public ExerciseLogDto.ResGetDto getExLog (@PathVariable("id") Long id){
        ExerciseLog exerciseLog = exerciseLogService.find(id);
        List<Exercise> exercises = exerciseLog.getExerciseList();
        ExerciseLogDto.ResGetDto res = new ExerciseLogDto.ResGetDto();

        for (Exercise ex : exercises) {
            String exName = ex.getExName();
            int weight = ex.getWeight();
            int count = ex.getCount();
            res.getReqDtoList().add(new ExerciseLogDto.ReqDto(exName, weight, count));
        }
        return res;
    }


    /**
     * ExerciseLog 수정
     * */
    @PatchMapping("/{id}")
    public resDto fixExLog(@PathVariable("id") Long id, @RequestBody ExerciseLogDto.ReqDtoList req){
        Long fixId = exerciseLogService.fix(id, req);
        return new resDto(fixId);
    }


    /**
     * ExerciseLog 삭제
     * */
    @DeleteMapping("/{id}")
    public Long deleteExLog(@PathVariable("id") Long id){
        return exerciseLogService.delete(id);
    }


    static class resDto {
        Long id;

        public resDto(Long id) {
            this.id = id;
        }
    }

}

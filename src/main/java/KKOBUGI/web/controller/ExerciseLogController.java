package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.Exercise;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.service.ExerciseLogService;
import KKOBUGI.web.service.ExerciseService;
import KKOBUGI.web.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExerciseLogController {

    private final ExerciseLogService exerciseLogService;
    private final UserService userService;
    private final ExerciseService exerciseService;

    /**
     * ExerciseLog 저장
     * */
    @PostMapping("/")
    public resDto saveExLog(@RequestBody ExerciseLogDto.ReqDtoList req) {
        ExerciseLog exerciseLog = new ExerciseLog();
        exerciseLog.setUser(userService.findOne(1L));
        exerciseLog.setCreateDate(LocalDateTime.now());
        List<ExerciseLogDto.ReqDto> requestExLogDtoList = req.getReqDtoList();
        for (ExerciseLogDto.ReqDto r : requestExLogDtoList) {
            Exercise exercise = new Exercise();
            exercise.setExercise(r.getExName(),r.getWeight(),r.getCount());
            exercise.setExerciseLog(exerciseLog);
        }
        exerciseLogService.save(exerciseLog);
        return new resDto(exerciseLog.getId());
    }


    /**
     * ExerciseLog 조회
     * */
    @GetMapping("/{id}")
    public ExerciseLogDto.ResGetDto getExLog (@PathVariable("id") Long id){
        ExerciseLog exerciseLog = exerciseLogService.find(id);
        List<Exercise> exercises = exerciseLog.getExerciseList();
        for (Exercise ex : exercises) {
            System.out.println(ex.getId());
        }
        ExerciseLogDto.ResGetDto res = new ExerciseLogDto.ResGetDto();
        List<ExerciseLogDto.ReqDto> reqDtoList = new ArrayList<>();
        for (Exercise ex : exercises) {
            String exName = ex.getExName();
            int weight = ex.getWeight();
            int count = ex.getCount();
            reqDtoList.add(new ExerciseLogDto.ReqDto(exName,weight,count));
        }
        res.setReqDtoList(reqDtoList);
        return res;
    }


    /**
     * ExerciseLog 수정
     * */
    @PatchMapping("/{id}")
    public resDto fixExLog(@PathVariable("id") Long id, @RequestBody ExerciseLogDto.ReqDtoList req){
        exerciseService.delete(id);
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


    @Data
    static class resDto {
        Long id;

        public resDto(Long id) {
            this.id = id;
        }
    }

}

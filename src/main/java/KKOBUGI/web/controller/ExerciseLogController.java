package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.service.ExerciseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseLogController {
    @Autowired
    ExerciseLogService exerciseLogService;

    /*ok
     * 운동 저장*/
    @PostMapping("calendar/{month}/{day}")
    public ExerciseLogDto saveExerciseLog(@PathVariable Long month, @PathVariable Long day,
                                          @RequestBody ExerciseLog exerciseLog){

        ExerciseLogDto exerciseLogDto = exerciseLogService.saveExerciseLog(exerciseLog, month, day);
        return exerciseLogDto;
    }

    /*ok
     * day에 따른 하루 운동기록 조회
     * */
    @GetMapping("calendar/{month}/{day}")
    public List<ExerciseLogDto> getExerciseLogByDay(@PathVariable Long month, @PathVariable Long day){
        List<ExerciseLogDto> exerciseLogDtoList =exerciseLogService.getExerciseLogByday(month, day);
        return exerciseLogDtoList;
    }

    /*month에 따른 한달간의 운동기록 조회
     * ok
     * */
    @GetMapping("calendar/{month}")
    public List<ExerciseLogDto> getExerciseLogByMonth(@PathVariable Long month){
        List<ExerciseLogDto> exerciseLogDtoList = exerciseLogService.getExerciseLogByMonth(month);
        return exerciseLogDtoList;
    }

    /*수정
     * ok*/
    @PatchMapping("calendar/{month}/{day}/{exerciseLogId}")
    public ExerciseLogDto updateExerciseLog(@PathVariable Long exerciseLogId
            ,@RequestBody ExerciseLog exerciseLog){
        ExerciseLogDto  updatedExerciseLogDto = exerciseLogService.updateExerciseLog(exerciseLogId, exerciseLog.getContent(),
                exerciseLog.getDetailLog(), exerciseLog.getNumber());
        return updatedExerciseLogDto;
    }
    /*하루  운동기록 삭제
     * -ok*/
    @DeleteMapping("calendar/{month}/{day}")
    public void deleteExerciseLogByDate(@PathVariable Long month, @PathVariable Long day){
        exerciseLogService.deleteExerciseLogByday(month,day);
    }

}
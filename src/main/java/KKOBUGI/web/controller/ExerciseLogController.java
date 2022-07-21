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
    @PostMapping("calendar/{date}")
   //입력: date, time, content, detailLog, number
    public ExerciseLogDto saveExerciseLog(@RequestBody ExerciseLog exerciseLog){
        ExerciseLogDto exerciseLogDto = exerciseLogService.saveExerciseLog(exerciseLog);
        return exerciseLogDto;
    }
    /*ok
     * day에 따른 하루 운동기록 조회
     * */
    @GetMapping("calendar/{date}")
    public List<ExerciseLogDto> getExerciseLogByDay(@PathVariable int date){
        List<ExerciseLogDto> exerciseLogDtoList =exerciseLogService.getExerciseLogByDay(date);
        return exerciseLogDtoList;
    }


    /*수정
     * ok*/
    //입력: date, time, content, detailLog, number
    @PatchMapping("calendar/{date}/{exerciseLogId}")
    public ExerciseLogDto updateExerciseLog(@PathVariable int date, @PathVariable Long exerciseLogId
            ,@RequestBody ExerciseLog exerciseLog){
        ExerciseLogDto  updatedExerciseLogDto = exerciseLogService.updateExerciseLog(exerciseLogId, exerciseLog.getContent(),
                exerciseLog.getDetailLog(), exerciseLog.getNumber());
        return updatedExerciseLogDto;
    }



    /*하루  운동기록 삭제
     * -ok*/
    @DeleteMapping("calendar/{date}")
    public void deleteExerciseLogByDate(@PathVariable int date){
        exerciseLogService.deleteExerciseLogByDate(date);
    }


    /* *//*month에 따른 한달간의 운동기록 조회
     * ok
     * *//*
    @GetMapping("calendar/{month}")
    public List<ExerciseLogDto> getExerciseLogByMonth(@PathVariable Long month){
        List<ExerciseLogDto> exerciseLogDtoList = exerciseLogService.getExerciseLogByMonth(month);
        return exerciseLogDtoList;
    }*/

}
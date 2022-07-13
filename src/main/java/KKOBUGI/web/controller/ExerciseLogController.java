package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.service.ExerciseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExerciseLogController {
    @Autowired
    ExerciseLogService exerciseLogService;

    /*
    * 저장*/
    @PostMapping("calendar/{month}/{day}")
    public ExerciseLogDto saveExerciseLog(@PathVariable Long month, @PathVariable Long day,
                                          @RequestBody ExerciseLogDto exerciseLogDto){
        return null;
    }
    /*
    * day에 따른 하루 운동기록 조회*/
    @GetMapping("calendar/{month}/{day}")
    public ExerciseLogDto getExerciseLogByDay(@PathVariable Long month, @PathVariable Long day){
        return null;
    }

    /*month에 따른 한달간의 운동기록 조회*/
    @GetMapping("calendar/{month}")
    public ExerciseLogDto getExerciseLogByMonth(@PathVariable Long month){
        return null;
    }

    /*수정*/
    @PatchMapping("calendar/{month}/{day}")
    public ExerciseLogDto updateExerciseLog(){
        return null;
    }
    /*삭제*/
    @DeleteMapping("calendar/{month}/{day}")
    public void deleteExerciseLog(){

    }
}

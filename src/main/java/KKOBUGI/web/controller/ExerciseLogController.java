package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.service.ExerciseLogService;
import KKOBUGI.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseLogController {
    @Autowired
    ExerciseLogService exerciseLogService;
    @Autowired
    UserService userService;

    /*ok
     * 운동 저장*/
    @PostMapping("calendar/{date}")
    //입력: userId, date, time, content, detailLog, number
    public ExerciseLogDto.ExerciseLogResponseDto saveExerciseLog(@RequestBody ExerciseLogDto.ExerciseLogRequestDto requestDto) {
        User user = userService.findOne(requestDto.userId);
        ExerciseLogDto.ExerciseLogResponseDto exerciseLogDto = exerciseLogService.saveExerciseLog(requestDto, user);
        return exerciseLogDto;
    }

    /*ok
     * day에 따른 하루 운동기록 조회
     * */
    @GetMapping("calendar/{date}/{userId}")
    public List<ExerciseLogDto.ExerciseLogResponseDto> getExerciseLogByDay(@PathVariable int date,
                                                                           @PathVariable Long userId){
        List<ExerciseLogDto.ExerciseLogResponseDto> exerciseLogDtoList = exerciseLogService.getExerciseLogByDay(date, userId);
        return exerciseLogDtoList;
    }

    /*하루  운동기록 삭제
     * -ok*/
    @DeleteMapping("calendar/{date}/{userId}")
    public void deleteExerciseLogByDate(@PathVariable int date, @PathVariable Long userId) {
        exerciseLogService.deleteExerciseLogByDate(date, userId);
    }

    /*수정
     * ok*/
    //입력: content, detailLog, number
    @PatchMapping("calendar/{date}/{exerciseLogId}")
    public ExerciseLogDto.ExerciseLogResponseDto updateExerciseLog(@PathVariable int date, @PathVariable Long exerciseLogId
            , @RequestBody ExerciseLog exerciseLog) {
        ExerciseLogDto.ExerciseLogResponseDto updatedExerciseLogDto = exerciseLogService.updateExerciseLog(exerciseLogId, exerciseLog.getContent(),
                exerciseLog.getDetailLog(), exerciseLog.getNumber());
        return updatedExerciseLogDto;
    }


}
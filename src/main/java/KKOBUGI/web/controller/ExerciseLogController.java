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
    public ExerciseLogDto.ExerciseLogListDto saveExerciseLog(@PathVariable int date,
                                                             @RequestBody ExerciseLogDto.ExerciseLogListDto listDto){
        User user = userService.findOne(listDto.userId);
        deleteExerciseLogByDate(date, listDto.getUserId());
        exerciseLogService.saveExerciseLog(listDto,user, date);
        return listDto;
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

    //개별삭제
    @DeleteMapping("calendar/{exerciseLogId}")
    public void deleteExerciseLogByDate(@PathVariable Long exerciseLogId) {
        exerciseLogService.deleteExerciseLogByExerciseLogId(exerciseLogId);
    }

    /*수정
     * ok*/
    //입력: content, detailLog, number
    @PatchMapping("calendar/{exerciseLogId}")
    public ExerciseLogDto.ExerciseLogResponseDto updateExerciseLog(@PathVariable Long exerciseLogId
            , @RequestBody ExerciseLog exerciseLog) {
        ExerciseLogDto.ExerciseLogResponseDto updatedExerciseLogDto = exerciseLogService.updateExerciseLog(exerciseLogId, exerciseLog.getContent(),
                exerciseLog.getDetailLog(), exerciseLog.getNumber());
        return updatedExerciseLogDto;
    }
    /*하루  운동기록 삭제
     * -ok*/
    @DeleteMapping("calendar/{date}/{userId}")
    public void deleteExerciseLogByDate(@PathVariable int date, @PathVariable Long userId) {
        exerciseLogService.deleteExerciseLogByDate(date, userId);
    }
}
//           "userId":1,
//           "time": "1:40h",
//           "content":"푸시업",
//           "detailLog":"20kg",
//           "number":10
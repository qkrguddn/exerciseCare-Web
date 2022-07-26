package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.BoardDto;
import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.repository.ExerciseLogRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ToString
@Service
@Transactional
public class ExerciseLogService {
    @Autowired
    ExerciseLogRepository exerciseLogRepository;

    /**
     * 저장
     */
    public ExerciseLogDto.ExerciseLogResponseDto saveExerciseLog(ExerciseLogDto.ExerciseLogRequestDto exerciseLogRequestDtoDto, User user) {
        ExerciseLog exerciseLog = new ExerciseLog(exerciseLogRequestDtoDto.getContent(), exerciseLogRequestDtoDto.getDetailLog(),
                exerciseLogRequestDtoDto.getNumber(),
                exerciseLogRequestDtoDto.getDate(), exerciseLogRequestDtoDto.getTime(), user);
        ExerciseLog savedExerciseLog = exerciseLogRepository.save(exerciseLog);

        ExerciseLogDto.UserDto userDto = UserToUserDto(user);
        ExerciseLogDto.ExerciseLogResponseDto exerciseLogResponseDto = exerciseLogToExerciseLogDto(savedExerciseLog);

        return exerciseLogResponseDto;
    }

    /*
     * 수정
     * */
    public ExerciseLogDto.ExerciseLogResponseDto updateExerciseLog(Long exerciseLogId, String content, String detailLog, Long number) {
        ExerciseLog exerciseLog = exerciseLogRepository.findById(exerciseLogId).get();
        exerciseLog.changeExerciseLog(content, detailLog, number);
        ExerciseLogDto.ExerciseLogResponseDto exerciseLogResponseDto =
                exerciseLogToExerciseLogDto(exerciseLog);

        return exerciseLogResponseDto;
    }

    /*
     * day에 따른 하루 운동기록 조회
     * */
    public List<ExerciseLogDto.ExerciseLogResponseDto> getExerciseLogByDay(int date, Long userId) {
        List<ExerciseLog> exerciseLogList = exerciseLogRepository.findAllByDate(date);
        List<ExerciseLogDto.ExerciseLogResponseDto> exerciseLogDtoList = new ArrayList<>();
        for (ExerciseLog exerciseLog : exerciseLogList) {
            if (exerciseLog.getUser().getId() == userId)
                exerciseLogDtoList.add(exerciseLogToExerciseLogDto(exerciseLog));
        }

        return exerciseLogDtoList;
    }

    /*
     * 삭제
     * */
    public void deleteExerciseLogByDate(int date, Long userId) {
        List<ExerciseLog> exerciseLogList = exerciseLogRepository.findAllByDate(date);
        for (ExerciseLog exerciseLog : exerciseLogList) {
            if (exerciseLog.getUser().getId() == userId) {
                exerciseLogRepository.deleteById(exerciseLog.getId());
            }

        }
    }

    /**
     * Entity -> dto 클래스 변환 함수
     */
    public static ExerciseLogDto.UserDto UserToUserDto(User user) {
        return new ExerciseLogDto.UserDto(user.getId(), user.getLogin_Id(), user.getLogin_Pw(), user.getNickname());
    }

    public static ExerciseLogDto.ExerciseLogResponseDto exerciseLogToExerciseLogDto(ExerciseLog exerciseLog) {
        return new ExerciseLogDto.ExerciseLogResponseDto(exerciseLog.getContent(), exerciseLog.getDetailLog()
                , exerciseLog.getNumber(), exerciseLog.getDate(), exerciseLog.getTime(),
                UserToUserDto(exerciseLog.getUser())
        );
    }
}

//entity -> dto 변환
 /*   public ExerciseLogDto toExerciseLogDto(ExerciseLog exerciseLog) {
        return ExerciseLogDto.builder()
                .exerciseLogId(exerciseLog.getId())
                .content(exerciseLog.getContent())
                .detailLog(exerciseLog.getDetailLog())
                .number(exerciseLog.getNumber())
                .date(exerciseLog.getDate())
                .time(exerciseLog.getTime())
                .build();
    }*/

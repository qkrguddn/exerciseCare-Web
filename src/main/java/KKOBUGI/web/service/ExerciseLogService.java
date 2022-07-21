package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.ExerciseLog;
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
     * 저장: 운동이름, 무게, 횟수, 날짜, 시간
     */
    public ExerciseLogDto saveExerciseLog(ExerciseLog exerciseLog) {
        exerciseLog = new ExerciseLog(exerciseLog.getContent(), exerciseLog.getDetailLog(), exerciseLog.getNumber(),
                exerciseLog.getDate(), exerciseLog.getTime());
        ExerciseLog savedExerciseLog = exerciseLogRepository.save(exerciseLog);
        ExerciseLogDto exerciseLogDto = toExerciseLogDto(savedExerciseLog);

        return exerciseLogDto;
    }

    /*
     * day에 따른 하루 운동기록 조회*/
    public List<ExerciseLogDto> getExerciseLogByDay(int date) {
        List<ExerciseLog> exerciseLogList = exerciseLogRepository.findAllByDate(date);
        List<ExerciseLogDto> exerciseLogDtoList = new ArrayList<>();
        for (ExerciseLog exerciseLog : exerciseLogList) {
            exerciseLogDtoList.add(toExerciseLogDto(exerciseLog));
        }

        return exerciseLogDtoList;
    }

    /*
     * 수정
     * */
    public ExerciseLogDto updateExerciseLog(Long exerciseLogId, String content, String detailLog, Long number) {
        ExerciseLog exerciseLog = exerciseLogRepository.findById(exerciseLogId).get();
        exerciseLog.changeExerciseLog(content, detailLog, number);
        ExerciseLogDto exerciseLogDto = toExerciseLogDto(exerciseLog);

        return exerciseLogDto;
    }

    /*
     * 삭제
     * delete에서 id, day, 운동이름 어떤 것 기준으로 삭제할 지 정해야함
     * */
    //하루 기록 삭제
    public void deleteExerciseLogByDate(int date) {
        exerciseLogRepository.deleteByDate(date);
    }


    //entity -> dto 변환
    public ExerciseLogDto toExerciseLogDto(ExerciseLog exerciseLog) {
        return ExerciseLogDto.builder()
                .exerciseLogId(exerciseLog.getId())
                .content(exerciseLog.getContent())
                .detailLog(exerciseLog.getDetailLog())
                .number(exerciseLog.getNumber())
                .date(exerciseLog.getDate())
                .time(exerciseLog.getTime())
                .build();
    }
    /*
     * month에 따른 한달 운동기록 전체 조회*/
//    public List<ExerciseLogDto> getExerciseLogByMonth(Long month) {
//        List<ExerciseLog> exerciseLogList = exerciseLogRepository.findAllByMonth(month);
//        List<ExerciseLogDto> exerciseLogDtoList = new ArrayList<>();
//        for (ExerciseLog exerciseLog : exerciseLogList) {
//            exerciseLogDtoList.add(toExerciseLogDto(exerciseLog));
//        }
//        return exerciseLogDtoList;
//    }
}

/*
 * 등록: input: 운동이름, 무게 or 시간, 횟수 / Return: 입력3개, date(month.day), month
 * 수정: input: 운동이름, 무게 or 시간, 횟수 / Return: 입력3개, date(month.day), month
 * 삭제: deletebydate
 * 조회1(day에 따른 리스트 조회): findAllbydate
 * 조회2(month에 따른 모든 리스트 조회): findAllbymonth
 * */
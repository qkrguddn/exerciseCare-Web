package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.repository.ExerciseLogRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@ToString
@Service
@Transactional
public class ExerciseLogService {
    @Autowired
    ExerciseLogRepository exerciseLogRepository;
    @Autowired
    EntityManager em;
    /**
     * 저장
     */
    public Long saveExerciseLog(ExerciseLogDto.ExerciseLogListDto listDto,
                                User user, int date) {
        for(ExerciseLogDto.InnerData innerData: listDto.getList()) {
            ExerciseLog exerciseLog = new ExerciseLog(innerData.getContent(), innerData.getDetailLog()
                    , innerData.getNumber(), date, listDto.getTime(), user);
            ExerciseLog savedExerciseLog = exerciseLogRepository.save(exerciseLog);
        }


        return listDto.userId;
    }
    /*public ExerciseLogDto.ExerciseLogResponseDto saveExerciseLog(ExerciseLogDto.ExerciseLogRequestDto exerciseLogRequestDtoDto,
                                                                 User user, int date) {
        ExerciseLog exerciseLog = new ExerciseLog(exerciseLogRequestDtoDto.getContent(), exerciseLogRequestDtoDto.getDetailLog(),
                exerciseLogRequestDtoDto.getNumber(),
                date, exerciseLogRequestDtoDto.getTime(), user);
        ExerciseLog savedExerciseLog = exerciseLogRepository.save(exerciseLog);
        ExerciseLogDto.UserDto userDto = UserToUserDto(user);
        ExerciseLogDto.ExerciseLogResponseDto exerciseLogResponseDto = exerciseLogToExerciseLogDto(savedExerciseLog);
        return exerciseLogResponseDto;
    }*/

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
    public void removeExerciseLog(Long exerciseLogId) {
        ExerciseLog exerciseLog = em.find(ExerciseLog.class, exerciseLogId);
        em.remove(exerciseLog);

    }
    /*개별 삭제*/
    public void deleteExerciseLogByExerciseLogId(Long exerciseLogId){
        removeExerciseLog(exerciseLogId);
    }

    /*
     * 하루삭제
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
        return new ExerciseLogDto.ExerciseLogResponseDto(exerciseLog.getId(),exerciseLog.getContent(), exerciseLog.getDetailLog()
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
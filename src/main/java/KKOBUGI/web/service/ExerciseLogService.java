package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.repository.ExerciseLogRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ToString
@Service
@Transactional
public class ExerciseLogService {
    @Autowired
    ExerciseLogRepository exerciseLogRepository;

    /**
     * 저장
     */
    public ExerciseLogDto saveExerciseLog(ExerciseLog exerciseLog, Long month, Long day){
        //각 입력 값 저장 후 dto변환하고 return
        return null;
    }
    /*
    * 삭제
    * delete에서 id, day, 운동이름 어떤 것 기준으로 삭제할 지 정해야함
    * */
    public void deleteExerciseLog(Long id){
        exerciseLogRepository.deleteById(id);
    }
    /*
    * day에 따른 하루 운동기록 조회*/
    public ExerciseLogDto getExerciseLogByday(String date){
        //findAllBydate
        return null;
    }
    /*
    * month에 따른 한달 운동기록 전체 조회*/
    public ExerciseLogDto getExerciseLogByMonth(Long month){
        return  null;
    }


    /*
    * 수정
    * */
    public ExerciseLogDto updateExerciseLog(){
        //findbyDateAnd운동이름으로 운동기록 찾고 엔티티에 change함수 만들어서 변경할 것들 바꾸고 dto저장
        return  null;
    }
}

/*
* 등록: input: 운동이름, 무게 or 시간, 횟수 / Return: 입력3개, date(month.day), month
* 수정: input: 운동이름, 무게 or 시간, 횟수 / Return: 입력3개, date(month.day), month
* 삭제: deletebydate
* 조회1(day에 따른 리스트 조회): findAllbydate
* 조회2(month에 따른 모든 리스트 조회): findAllbymonth
* */
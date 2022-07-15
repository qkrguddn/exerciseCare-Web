package KKOBUGI.web.repository;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.Exercise;
import KKOBUGI.web.domain.entity.ExerciseLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExerciseLogRepository {

    private final EntityManager em;

    /**
     * 저장 */
    public Long save(ExerciseLog exerciseLog) {
        em.persist(exerciseLog);
        return exerciseLog.getId();
    }

    /**
     * 조회 */
    public ExerciseLog find(Long id){
        return em.find(ExerciseLog.class, id);
    }

    /**
     * 수정 */
    public Long fix(Long id, ExerciseLogDto.ReqDtoList req){
        ExerciseLog exLog = em.find(ExerciseLog.class, id);
        exLog.fixExerciseLog(req);
        return exLog.getId();
    }

    /**
     * 삭제 */
    public Long delete(Long id){
        ExerciseLog exerciseLog = em.find(ExerciseLog.class, id);
        em.remove(exerciseLog);
        return exerciseLog.getId();
    }
}

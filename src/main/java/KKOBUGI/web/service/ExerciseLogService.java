package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.ExerciseLogDto;
import KKOBUGI.web.domain.entity.Exercise;
import KKOBUGI.web.domain.entity.ExerciseLog;
import KKOBUGI.web.repository.ExerciseLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExerciseLogService {

    private ExerciseLogRepository exerciseLogRepository;

    /**
     * 저장 */
    @Transactional
    public Long save(ExerciseLog exerciseLog){
        return exerciseLogRepository.save(exerciseLog);
    }


    /**
     * 조회 */
    public ExerciseLog find(Long id){
        return exerciseLogRepository.find(id);
    }

    /**
     * 수정 */
    @Transactional
    public Long fix(Long id, ExerciseLogDto.ReqDtoList req){
        return exerciseLogRepository.fix(id, req);
    }

    /**
     * 삭제
     */
    @Transactional
    public Long delete(Long id){
        return exerciseLogRepository.delete(id);
    }

}

package KKOBUGI.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Transactional
    public Long save(Exercise ex){
        return exerciseRepository.save(ex);
    }

    @Transactional
    public void delete(Long exLogId){
        exerciseRepository.delete(exLogId);
    }
}

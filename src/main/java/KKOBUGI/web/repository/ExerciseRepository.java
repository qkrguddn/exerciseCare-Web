package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.Exercise;
import KKOBUGI.web.domain.entity.ExerciseLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExerciseRepository {

    private final EntityManager em;

    public Long save(Exercise ex){
        em.persist(ex);
        return ex.getId();
    }

    public void delete(Long exLogId){
        List<Exercise> results = em.createQuery("select e from Exercise e where e.exerciseLog.id = :exLogId", Exercise.class)
                .setParameter("exLogId",exLogId).getResultList();
        for (Exercise r : results) {
            em.remove(r);
        }
    }
}

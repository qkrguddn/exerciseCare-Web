package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.domain.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {

    void deleteByDate(int date);
    List<ExerciseLog> findAllByDate(int date);

}
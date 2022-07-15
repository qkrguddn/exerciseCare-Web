package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.domain.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    public void deleteByDate(String date);
    public void deleteByDateAndContent(String date, String content);

    List<ExerciseLog> findAllByDateAndContentAndDetailLogAndNumber(
            String date, String content, String detailLog, Long number);
    List<ExerciseLog> findAllByDate(String date);
    List<ExerciseLog> findAllByMonth(Long month);
}
package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}

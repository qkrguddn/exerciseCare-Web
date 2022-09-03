package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>  {

}

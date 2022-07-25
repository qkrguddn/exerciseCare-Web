package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    /**
     * Comment 생성 */
    public void save(Comment comment){
        em.persist(comment);
        comment.getBoard().getComments().add(comment);  // board 에 comment 추가
    }

    /**
     * Comment 삭제 */
    public Long remove(Long commentId){
        Comment comment = em.find(Comment.class, commentId);
        em.remove(comment);
        return commentId;
    }

    /**
     * 조회 */
    public Comment findOne(Long commentId){
        return em.find(Comment.class, commentId);
    }

}

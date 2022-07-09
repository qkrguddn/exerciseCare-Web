package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    /**
     * 저장 */
    public Long save(Comment comment){
        em.persist(comment);
        return comment.getId();
    }


    /**
     * 조회 */
    public Comment findOne(Long id){
        return em.find(Comment.class,id);
    }

    public List<Comment> findByBoardId(Long boardId){
        List<Comment> comments = em.createQuery("select c from Comment c join c.board b where b.id = :id", Comment.class)
                .setParameter("id", boardId).setMaxResults(20).getResultList();
        return comments;
    }


    /**
     * 수정 */
    public Long update(Long id, Comment c){
        Comment comment = em.find(Comment.class, id);
        comment.setContent(c.getContent());
        return comment.getId();
    }


    /**
     * 삭제 */
    public Long delete(Long id){
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
        return comment.getId();
    }
}

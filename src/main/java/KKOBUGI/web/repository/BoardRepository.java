package KKOBUGI.web.repository;

import KKOBUGI.web.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board){
        em.persist(board);
    }

    // user_Id 로 find
    public List<Board> findAll(Long userId){
        return em.createQuery("select b from Board b join b.user u where u.id = :userId")
                .setParameter("userId", userId)
                .setMaxResults(20)
                .getResultList();
    }

    // board_Id 로 find
    public Board findOne(Long id){
        return em.find(Board.class, id);
    }

    // title 로 찾음
    public List<Board> findByTitle(String title){
        return em.createQuery("select b from Board b where b.title = :title")
                .setParameter("title", title).getResultList();
    }
}

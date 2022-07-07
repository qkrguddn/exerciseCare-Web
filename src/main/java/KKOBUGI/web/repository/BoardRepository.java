package KKOBUGI.web.repository;

import KKOBUGI.web.domain.Board;
import KKOBUGI.web.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;
    private final UserRepository userRepository;

    /** board 저장
     * */
    public void save(Board board){
        em.persist(board);
    }


    /** 게시판 삭제
     * */
    public Long remove(Long boardId) {
        Board findBoard = em.find(Board.class, boardId);
        em.remove(findBoard);
        return boardId;
    }


    /**
     * 게시판 조회
     * */
    // 모든 글 조회
    public List<Board> findAll(){
        List<Board> boards = em.createQuery("select b from Board b", Board.class).setMaxResults(20).getResultList();
        return boards;
    }


//    // userId 로 find
//    public List<Board> findAll(Long userId){
//        return em.createQuery("select b from Board b join b.user u where u.id = :userId")
//                .setParameter("userId", userId)
//                .setMaxResults(20)
//                .getResultList();
//    }

    // board_Id 로 find
    public Board findOne(Long id){
        return em.find(Board.class, id);
    }

    // title 로 찾음
    public List<Board> findByTitle(String title){
        return em.createQuery("select b from Board b where b.title = :title")
                .setParameter("title", title).setMaxResults(20)
                .getResultList();
    }

}

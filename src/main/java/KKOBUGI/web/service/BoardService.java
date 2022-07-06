package KKOBUGI.web.service;

import KKOBUGI.web.domain.Board;
import KKOBUGI.web.domain.User;
import KKOBUGI.web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * board 저장  */
    @Transactional
    public Long save(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    /**
     *  게시판 수정  */
    @Transactional
    public void fixBoard(Long boardId, String title, String content, LocalDateTime modifyDate){
        Board board = boardRepository.findOne(boardId);
        board.setTitle(title);
        board.setContent(content);
        board.setModifyDate(modifyDate);
    }

    /**
     * 게시판 삭제  */
    @Transactional
    public void remove(Long boardId){
        boardRepository.remove(boardId);
    }


    /**
     * 게시판 조회
     * */
    // 한 유저가 작성한 게시판 모두 불러옴
    public List<Board> findAll(User user){
        return boardRepository.findAll(user.getId());
    }

    // board id로 찾음
    public Board findOne(Long id){
        return boardRepository.findOne(id);
    }

    // 제목으로 찾음
    public List<Board> findByTitle(String title){
        return boardRepository.findByTitle(title);
    }

}

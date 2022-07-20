package KKOBUGI.web.service;

import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.repository.BoardRepository;
import KKOBUGI.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

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
    public void fixBoard(Long boardId, String title, String content){
        Board board = boardRepository.findOne(boardId);
        board.setBoard(title,content);
    }

    /**
     * 게시판 삭제  */
    @Transactional
    public Long remove(Long boardId){
        return boardRepository.remove(boardId);
    }


    /**
     * 게시판 조회
     * */
    // 모든 글 조회.
    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    // board id로 찾음 (필수)
    public Board findById(Long id){
        return boardRepository.findOne(id);
    }

    // 제목으로 찾음
    public List<Board> findByTitle(String title){
        return boardRepository.findByTitle(title);
    }

}

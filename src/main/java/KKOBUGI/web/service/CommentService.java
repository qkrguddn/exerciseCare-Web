package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.BoardDetailDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.repository.BoardRepository;
import KKOBUGI.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    /**
     * 댓글 조회 */
    public Comment findOne(Long commentId){
        return commentRepository.findOne(commentId);
    }

    /**
     * 댓글 생성 */
    @Transactional
    public void save(Comment comment){
        commentRepository.save(comment);
    }


    /**
     * 댓글 삭제 */
    @Transactional
    public Long delete(Long boardId, Long commentId){
        return commentRepository.remove(commentId);
    }


    /**
     * 댓글 수정 */
    @Transactional
    public Long fix(Long commentId, CommentDto.ReqFixDto req){
        Comment comment = commentRepository.findOne(commentId);
        comment.fixComment(req.getContent());
        return commentId;
    }
}
//entity로 저장 후 dto변환하고 dto반환.
  /*  public CommentDto saveComment(Long id, Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        CommentDto commentDto = comment.toCommentDto();

        return commentDto;
    }*/

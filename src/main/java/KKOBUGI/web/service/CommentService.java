package KKOBUGI.web.service;


import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * 댓글 조회 */
    public Comment findOne(Long commentId){
        return commentRepository.findById(commentId).get();
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
    public void delete(Long commentId){
         commentRepository.deleteById(commentId);
    }


    /**
     * 댓글 수정 */
    @Transactional
    public Long fix(Long commentId, CommentDto.ReqFixDto req){
        Comment comment = commentRepository.findById(commentId).get();
        comment.fixComment(req.getContent());
        return commentId;
    }
}


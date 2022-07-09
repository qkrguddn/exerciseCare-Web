package KKOBUGI.web.service;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 조회 */
    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }

    public List<Comment> findByBoardId(Long boardId){
        return commentRepository.findByBoardId(boardId);
    }

    /**
     * 생성 */
    @Transactional
    public Long save(Comment comment){
        return commentRepository.save(comment);
    }

    /**
     * 수정 */
    @Transactional
    public Long update(Long id, Comment comment){
        return commentRepository.update(id,comment);
    }

    /**
     * 삭제 */
    @Transactional
    public Long delete(Comment comment){
        return commentRepository.delete(comment.getId());
    }
}

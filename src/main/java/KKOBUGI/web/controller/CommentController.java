package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.repository.BoardRepository;
import KKOBUGI.web.repository.CommentRepository;
import KKOBUGI.web.service.BoardService;
import KKOBUGI.web.service.CommentService;
import KKOBUGI.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final BoardService boardService;

    /**
     * 저장 */
    @PostMapping("/board/{boardId}")
    public CommentDto.ResCreateDto saveComment(@PathVariable("boardId") Long id, @RequestBody CommentDto.ReqCreateDto req) {
        String content = req.getContent();
        Comment comment = new Comment();
        User user = userService.findOne(req.getUserId());
        Board board = boardService.findById(id);
        comment.setComment(req.getContent(),LocalDateTime.now(),user,board);
        commentService.save(comment);

        return new CommentDto.ResCreateDto(comment.getId(),comment.getContent());
    }

    /**
     * 삭제
     * : 삭제된 Comment의 id 값 반환 */
    @DeleteMapping("/board/{boardId}/{commentId}")
    public CommentDto.ResDeleteDto deleteComment(@PathVariable ("boardId") Long id,
                                                 @PathVariable ("commentId") Long commentId) {
        Board board = boardService.findById(id);
        board.eraseComment(commentService.findOne(commentId));
        return new CommentDto.ResDeleteDto(commentService.delete(id, commentId));
    }


    /**
     * 수정 */
    @PatchMapping("/board/{boardId}/{commentId}")
    public CommentDto.ResFixDto updateComment(@PathVariable("commentId") Long commentId,
                                              @RequestBody CommentDto.ReqFixDto req){
        return new CommentDto.ResFixDto(commentService.fix(commentId, req));
    }

}
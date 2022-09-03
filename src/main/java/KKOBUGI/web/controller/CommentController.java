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

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

import static KKOBUGI.web.domain.dto.CommentDto.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final BoardService boardService;

    /**
     * 저장 */
    @PostMapping("/board/{boardId}")
    public ResCreateDto saveComment(@PathVariable("boardId") Long id, @RequestBody @Valid ReqCreateDto req) {
        User user = userService.findOne(req.getUserId());
        Board board = boardService.findById(id);
       Comment comment = new Comment(req.getContent(),LocalDateTime.now(),user,board);
        commentService.save(comment);

        return new ResCreateDto(comment.getId(),comment.getContent());
    }

    /**
     * 삭제
     * : 삭제된 Comment의 id 값 반환 */
    @DeleteMapping("/board/{boardId}/{commentId}")
    public void deleteComment(@PathVariable ("commentId") Long commentId) {
         commentService.delete(commentId);
    }

    /**
     * 수정 */
    @PatchMapping("/board/{boardId}/{commentId}")
    public ResFixDto updateComment(@PathVariable("commentId") Long commentId,
                                   @RequestBody ReqFixDto req){
        return new ResFixDto(commentService.fix(commentId, req));
    }

}
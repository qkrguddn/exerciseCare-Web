package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.BoardDetailDto;
import KKOBUGI.web.domain.dto.BoardDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.repository.BoardRepository;
import KKOBUGI.web.repository.CommentRepository;
import KKOBUGI.web.service.BoardService;
import KKOBUGI.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 저장 */
    @PostMapping("/board/{id}")
    public CommentDto saveComent(@PathVariable Long id, @RequestBody CommentDto commentDto) { // @RequestBody: content, nickname
        CommentDto savedComment = commentService.saveComment(id, commentDto);
        return savedComment;
    }

    /**
     * 삭제 */
    @DeleteMapping("/board/{id}/{number}")
    public void deleteComment(@PathVariable Long number) {
        commentService.deleteComment(number);
    }


    /**
     * 수정 */
    @PatchMapping("/board/{id}/{number}")
    public CommentDto updateComment(@PathVariable Long id, @PathVariable Long number, @RequestBody CommentDto commentDto){ //@RequestBody: content
        CommentDto savedComment = commentService.updateComment(number, commentDto.getContent());
        return savedComment;
    }

    //세부사항 게시물 페이지(게시물 제목, 내용, 댓글목록) 조회
    @GetMapping("home/board/{id}")
    public BoardDetailDto getBoardCommentPage(@PathVariable Long id) {
        return commentService.getBoardDetail(id);
    }

}
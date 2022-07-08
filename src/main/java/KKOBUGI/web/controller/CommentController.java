package KKOBUGI.web.controller;

import KKOBUGI.web.domain.Board;
import KKOBUGI.web.domain.Comment;
import KKOBUGI.web.domain.User;
import KKOBUGI.web.domain.dto.CommentDtos;
import KKOBUGI.web.service.BoardService;
import KKOBUGI.web.service.CommentService;
import KKOBUGI.web.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;
    private final UserService userService;


    /**
     * Comment 생성 * */
    @PostMapping("/api/comment")
    public CommentDto save(@RequestBody RequestCommentDto request){
        String content = request.getContent();
        Long userId = request.getUserId();
        Long boardId = request.getBoardId();
        Comment comment = new Comment(content, LocalDateTime.now(), boardService.findById(boardId), userService.findOne(userId));
        commentService.save(comment);
        return CommentToCommentDto(comment);
    }


    /**
     * Comment 조회 : boardId로 조회 * */
    @GetMapping("/api/comment")
    public ResCommentFindDto findOne(@RequestBody ReqCommentFindDto req){
        Long boardId = req.getBoardId();
        List<Comment> comments = commentService.findByBoardId(boardId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment c : comments) {
            CommentDto commentDto = CommentToCommentDto(c);
            commentDtos.add(commentDto);
        }
        return new ResCommentFindDto(commentDtos);
    }


    /**
     * Comment 삭제 */
    @DeleteMapping("/api/comment/{id}")
    public Long delete(@PathVariable("id") Long id){
        Comment comment = commentService.findOne(id);
        Long deletedId = commentService.delete(comment);
        return deletedId;
    }


    /**
     * Comment 수정 */
    @PatchMapping("/api/comment")
    public Long update(@RequestBody CommentDtos.CommentFixReqDto req){
        Long id = req.getId();
        String content = req.getContent();
        Comment comment = commentService.findOne(id);
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        return commentService.update(id, comment);
    }


    public CommentDto CommentToCommentDto(Comment c){
        return new CommentDto(c.getId(),c.getContent(),c.getCreateDate(),c.getBoard(),c.getUser());
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    static class RequestCommentDto{
        private String content;
        private Long userId;
        private Long boardId;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    static class CommentDto{
        private Long id;
        private String content;
        private LocalDateTime createDate;
        private Board board;
        private User user;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    static class ReqCommentFindDto{
        private Long boardId;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    static class ResCommentFindDto{
        private List<CommentDto> commentDtos;
    }
}

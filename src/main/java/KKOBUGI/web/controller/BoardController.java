package KKOBUGI.web.controller;

import KKOBUGI.web.domain.Board;
import KKOBUGI.web.domain.Comment;
import KKOBUGI.web.domain.User;
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
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;
    private final CommentService commentService;


    /**
     * 게시판 생성 */
    @PostMapping("/api/board")
    public BoardDto saveBoard(@RequestBody BoardRequestDto request){
        User user = userService.findOne(request.userId);
        Board board = new Board(request.title, request.content,LocalDateTime.now(),null,user);
        Long boardId = boardService.save(board);

        UserDto userDto = UserToUserDto(user);
        BoardDto boardDto = BoardToBoardDto(board);
        return boardDto;
    }


    /**
     * 게시판  삭제 : boardId */
    @DeleteMapping("/api/board/{id}")
    public Long removeBoard(@PathVariable("id") Long id){
        Long removedId = boardService.remove(id);
        return removedId;
    }


    /**
     * 게시판 전체 조회
     * */
    @GetMapping("/api/board")
    public List<BoardDto> findAll(){
        List<Board> boards = boardService.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board b : boards) {
            UserDto userDto = UserToUserDto(b.getUser());
            boardDtos.add(new BoardDto(b.getId(),b.getTitle(),b.getContent(),b.getCreateDate(),b.getModifyDate(),userDto));
        }
        return boardDtos;
    }


    /**
     * boardID로 조회
     * */
    @GetMapping("/api/board/{id}")
    public BoardDto findById(@PathVariable("id") Long id){
        Board b = boardService.findById(id);
        UserDto userDto = UserToUserDto(b.getUser());
        BoardDto boardDto = BoardToBoardDto(b);
        return boardDto;
    }


    /**
     * 해당 글 세부 내용 보기
     * */
    @GetMapping("/api/board/{id}/detail")
    public ResponseBoardDetail boardDetail(@PathVariable("id") Long id){
        Board board = boardService.findById(id);
        BoardDto boardDto = BoardToBoardDto(board);
        List<Comment> comments = commentService.findByBoardId(id);
        return new ResponseBoardDetail(boardDto,comments);
    }


    /**
     * Entity -> dto 클래스 변환 함수
     * */
    public static UserDto UserToUserDto (User user){
        return new UserDto(user.getId(),user.getLogin_Id(),user.getLogin_Pw(),user.getNickname());
    }

    public static BoardDto BoardToBoardDto(Board board){
        return new BoardDto(board.getId(),board.getTitle(),board.getContent(),board.getCreateDate(),board.getModifyDate(),UserToUserDto(board.getUser()));
    }


    /**
     * dto
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class BoardRequestDto{
        private String title;
        private String content;
        private Long userId;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class BoardDto{
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;
        private UserDto user;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class UserDto{
        private Long id;
        private String login_Id;
        private String login_Pw;
        private String nickname;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ResponseBoardDetail{
        BoardDto boardDto;
        List<Comment> comments;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class CommentDto{
        private Long id;
        private String nickname;
        private String content;
        private LocalDateTime createDate;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class RequestBoard{
        Long boardId;
    }
}

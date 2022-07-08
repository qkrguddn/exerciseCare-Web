package KKOBUGI.web.controller;

import KKOBUGI.web.domain.Board;
import KKOBUGI.web.domain.Comment;
import KKOBUGI.web.domain.User;
import KKOBUGI.web.domain.dto.BoardDtos;
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
    public BoardDtos.BoardDto saveBoard(@RequestBody BoardDtos.BoardRequestDto request){
        User user = userService.findOne(request.userId);
        Board board = new Board(request.title, request.content,LocalDateTime.now(),null,user);
        Long boardId = boardService.save(board);

        BoardDtos.UserDto userDto = UserToUserDto(user);
        BoardDtos.BoardDto boardDto = BoardToBoardDto(board);
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
    public List<BoardDtos.BoardDto> findAll(){
        List<Board> boards = boardService.findAll();
        List<BoardDtos.BoardDto> boardDtos = new ArrayList<>();
        for (Board b : boards) {
            BoardDtos.UserDto userDto = UserToUserDto(b.getUser());
            boardDtos.add(new BoardDtos.BoardDto(b.getId(),b.getTitle(),b.getContent(),b.getCreateDate(),b.getModifyDate(),userDto));
        }
        return boardDtos;
    }


    /**
     * boardID로 조회
     * */
    @GetMapping("/api/board/{id}")
    public BoardDtos.BoardDto findById(@PathVariable("id") Long id){
        Board b = boardService.findById(id);
        BoardDtos.UserDto userDto = UserToUserDto(b.getUser());
        BoardDtos.BoardDto boardDto = BoardToBoardDto(b);
        return boardDto;
    }


    /**
     * 해당 글 세부 내용 보기
     * */
    @GetMapping("/api/board/{id}/detail")
    public BoardDtos.ResponseBoardDetail boardDetail(@PathVariable("id") Long id){
        Board board = boardService.findById(id);
        BoardDtos.BoardDto boardDto = BoardToBoardDto(board);
        List<Comment> comments = commentService.findByBoardId(id);
        return new BoardDtos.ResponseBoardDetail(boardDto,comments);
    }


    /**
     * Entity -> dto 클래스 변환 함수
     * */
    public static BoardDtos.UserDto UserToUserDto (User user){
        return new BoardDtos.UserDto(user.getId(),user.getLogin_Id(),user.getLogin_Pw(),user.getNickname());
    }

    public static BoardDtos.BoardDto BoardToBoardDto(Board board){
        return new BoardDtos.BoardDto(board.getId(),board.getTitle(),board.getContent(),board.getCreateDate(),board.getModifyDate(),UserToUserDto(board.getUser()));
    }
}

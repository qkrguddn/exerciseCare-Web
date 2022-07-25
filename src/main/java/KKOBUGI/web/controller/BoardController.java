package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.BoardDetailDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.domain.dto.BoardDto;
import KKOBUGI.web.service.BoardService;
import KKOBUGI.web.service.CommentService;
import KKOBUGI.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;
    private final CommentService commentService;

    /**
     * 게시판 생성 */
    @PostMapping("/board")
    public BoardDto.ResponseBoardDto saveBoard(@RequestBody BoardDto.BoardRequestDto request){
        User user = userService.findOne(request.userId);
        Board board = new Board(request.title, request.content,LocalDateTime.now(),null,user);
        Long boardId = boardService.save(board);
        user.saveBoard(boardService.findById(boardId));  // user에도 board추가

        BoardDto.ResponseBoardDto boardDto = BoardToBoardDto(board);
        return boardDto;
    }



    /**
     * 게시판 수정 */
    @PatchMapping("/board/{id}")
    public Long fixBoard(@PathVariable("id")Long id, @RequestBody BoardDto.BoardReqFixDto req){
        String title = req.title;
        String content = req.content;
        boardService.fixBoard(id,title,content);
        return id;
    }


    /**
     * 게시판  삭제 : boardId */
    @DeleteMapping("/board/{id}")
    public Long removeBoard(@PathVariable("id") Long id){
        Long removedId = boardService.remove(id);
        return removedId;
    }


    /**
     * 게시판 전체 조회
     * */
    @GetMapping("/board")
    public List<BoardDto.ResponseBoardDto> findAll(){
        List<Board> boards = boardService.findAll();
        List<BoardDto.ResponseBoardDto> boardDtos = new ArrayList<>();
        for (Board b : boards) {
            BoardDto.UserDto userDto = UserToUserDto(b.getUser());
            boardDtos.add(new BoardDto.ResponseBoardDto(b.getId(),b.getTitle(),b.getContent(),b.getCreateDate(),b.getModifyDate(),userDto));
        }
        return boardDtos;
    }


    /**
     * 세부사항 게시물 페이지 조회
     * : 게시판 id, 게시판 title, 게시판 content, CommentDtosList */
    @GetMapping("/board/{boardId}")
    public BoardDto.BoardDetailDto boardDetail(@PathVariable("boardId") Long id) {
        Board board = boardService.findById(id);
        List<BoardDto.CommentDtos> commentDtos = board.getComments().stream().map(o -> new BoardDto.CommentDtos(o.getId(), o.getContent())).collect(toList());
        return new BoardDto.BoardDetailDto(board.getId(), board.getTitle(), board.getContent(), commentDtos);
    }


    /**
     * Entity -> dto 클래스 변환 함수
     * */
    public static BoardDto.UserDto UserToUserDto (User user){
        return new BoardDto.UserDto(user.getId(),user.getLogin_Id(),user.getLogin_Pw(),user.getNickname());
    }

    public static BoardDto.ResponseBoardDto BoardToBoardDto(Board board){
        return new BoardDto.ResponseBoardDto(board.getId(),board.getTitle(),board.getContent(),board.getCreateDate(),board.getModifyDate(),UserToUserDto(board.getUser()));
    }
}
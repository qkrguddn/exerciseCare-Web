package KKOBUGI.web.controller;

import KKOBUGI.web.domain.dto.BoardDetailDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
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

        BoardDto.UserDto userDto = UserToUserDto(user);
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
     * boardID로 조회
     * */
    @GetMapping("/board/{id}")
    public BoardDto.ResponseBoardDto findById(@PathVariable("id") Long id){
        Board b = boardService.findById(id);
        BoardDto.UserDto userDto = UserToUserDto(b.getUser());
        BoardDto.ResponseBoardDto boardDto = BoardToBoardDto(b);
        return boardDto;
    }


//    /**
//     * 해당 글 세부 내용 보기
//     * */
//    @GetMapping("/board/{id}")
//    public BoardDetailDto boardDetail(@PathVariable("id") Long id){
//        BoardDetailDto boardDetail = commentService.getBoardDetail(id);
//        return boardDetail;
//    }



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

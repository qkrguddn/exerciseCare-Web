package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.BoardDetailDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.dto.CommentDto;
import KKOBUGI.web.domain.entity.Board;
import KKOBUGI.web.domain.entity.Comment;
import KKOBUGI.web.repository.BoardRepository;
import KKOBUGI.web.repository.CommentRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ToString
@Service
@Transactional
public class CommentService {
    /*등록, 수정, 삭제, 세부사항 게시판 페이지 조회*/
    public static ArrayList<ArrayList<Comment>> comments = new ArrayList<ArrayList<Comment>>();
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BoardRepository boardRepository;

    //세부사항 게시판 페이지 조회(게시판 제목, 내용, 모든 댓글목록)
    public BoardDetailDto getBoardDetail(Long boardId) {
        /*같은 게시판의 모든 댓글 List*/
        List<Comment> commentEneities = commentRepository.findAllByPostId(boardId);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentEneities) {
            commentDtoList.add(toCommentDto(comment));
        }
        /*해당 게시물의 제목과 내용 */
       /* Optional<Board> boardEntityWrapper = boardRepository.findById(boardId);
        Board board = boardEntityWrapper.get();*/
        Board board = boardRepository.findOne(boardId);
        System.out.println(board.getTitle() + "   " + board.getContent());

        BoardDetailDto detailPage
                = new BoardDetailDto(board.getTitle(), board.getContent(), commentDtoList); // 세부사항 게시판 페이지

        return detailPage;
    }

    //댓글 저장
    public CommentDto saveComment(Long postId, CommentDto commentDto) {
        commentDto = new CommentDto(postId, commentDto.getNickname(), commentDto.getContent(), commentDto.getCreateDate().now());
        Comment comment = commentDto.toEntity();
        Comment savedComment = commentRepository.save(comment);
        Long savedNumber = savedComment.getId();
        commentDto.setNumber(savedNumber);
        return commentDto;
    }

    //댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    //댓글 수정
    public CommentDto updateComment(Long id, String content) {
        Optional<Comment> commentEntityWrapper = commentRepository.findById(id);
        Comment comment = commentEntityWrapper.get();
        System.out.println("초기 comment: " + comment.getId() + "  " + comment.getContent());
        comment.changeComment(content);
        System.out.println("두번째 comment: " + comment.getId() + "  " + comment.getContent());
        CommentDto entityToCommentDto = comment.toCommentDto();
        return entityToCommentDto;
    }

    //entity -> dto 변환
    public CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .postId(comment.getPostId())
                .number(comment.getId())
                .nickname(comment.getNickname())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .build();
    }


}
//entity로 저장 후 dto변환하고 dto반환.
  /*  public CommentDto saveComment(Long id, Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        CommentDto commentDto = comment.toCommentDto();

        return commentDto;
    }*/

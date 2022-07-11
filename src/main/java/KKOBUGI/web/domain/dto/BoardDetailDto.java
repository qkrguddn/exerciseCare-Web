package KKOBUGI.web.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardDetailDto {
    private List<CommentDto> commentDtoList;
    private String boardTitle;
    private String boardContent;

    public BoardDetailDto(String boardTitle, String boardContent, List<CommentDto> commentDtoList) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.commentDtoList = commentDtoList;
    }

}
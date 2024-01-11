package com.skkudteam3.skkusirenorder.src.dto;

import com.skkudteam3.skkusirenorder.src.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private String createdBy;

    @Builder
    public BoardDTO(String title, String content, String createdBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .build();
    }

    public BoardDTO(Board board) {
        id = board.getId();
        title = board.getTitle();
        content = board.getContent();
    }

}
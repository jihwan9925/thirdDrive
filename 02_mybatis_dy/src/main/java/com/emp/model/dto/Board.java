package com.emp.model.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private int boardNo;
	private String boardTitle;
	private Member boardWriter;
	private String boardContent;
	private String boardOriginalFileName;
	private String boardRenamedFileName;
	private Date boardDate;
	private int boardReadCount;
	private List<BoardComment> boardComment;
}

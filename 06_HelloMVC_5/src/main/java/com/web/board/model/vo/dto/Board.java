package com.web.board.model.vo.dto;

import java.sql.Date;

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
	private String boardWriter;
//	private Member boardWriter;
	private String boardContent;
	private String boardOriginal;
	private String boardRenamed;
	private Date boardDate;
	private int boardReadCount;
}

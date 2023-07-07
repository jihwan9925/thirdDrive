SELECT * FROM BOARD JOIN BOARD_COMMENT ON board_no=board_comment_no;
SELECT * 
FROM BOARD 
JOIN BOARD_COMMENT ON board_no=board_ref 
JOIN MEMBER ON board_writer=userid
WHERE board_no=61;
SELECT * FROM BOARD;
SELECT * FROM BOARD_COMMENT;
SELECT * FROM member;
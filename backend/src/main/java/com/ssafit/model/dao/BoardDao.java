package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Board;

public interface BoardDao {
	void createBoard(Board board);
	
	Board getBoardById(Integer boardId);
	
	List<Board> getAllBoards();
	
	void updateBoard(Board board);

	void deleteBoard(Integer boardId);
}

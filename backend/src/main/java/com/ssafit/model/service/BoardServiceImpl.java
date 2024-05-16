package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.BoardDao;
import com.ssafit.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;

	public void createBoard(Board board) {
		boardDao.createBoard(board);
	}

	public Board getBoardById(int boardId) {
		return boardDao.getBoardById(boardId);
	}

	@Override
	public List<Board> getBoardByUserId(int userId) {
		return boardDao.getBoardByUserId(userId);
	}

	@Override
	public List<Board> getFollowingBoardByUserId(int userId) {
		return boardDao.getFollowingBoardByUserId(userId);
	}

	public List<Board> getAllBoards() {
		return boardDao.getAllBoards();
	}

	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	public void deleteBoard(int boardId) {
		boardDao.deleteBoard(boardId);
	}

}

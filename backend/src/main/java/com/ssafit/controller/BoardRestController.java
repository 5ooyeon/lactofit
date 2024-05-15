package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Board;
import com.ssafit.model.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	@PostMapping
	public ResponseEntity<Board> createBoard(@RequestBody Board board) {
		boardService.createBoard(board);
		return new ResponseEntity<>(board, HttpStatus.CREATED);
	}

	@GetMapping("/{board_id}")
	public ResponseEntity<Board> getBoardById(@PathVariable("board_id") int boardId) {
		Board board = boardService.getBoardById(boardId);
		if (board != null) {
			return new ResponseEntity<>(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<Board>> getAllBoards() {
		List<Board> boards = boardService.getAllBoards();
		return new ResponseEntity<>(boards, HttpStatus.OK);
	}

	@PutMapping("/{board_id}")
	public ResponseEntity<Board> updateBoard(@PathVariable("board_id") int boardId, @RequestBody Board board) {
		board.setBoardId(boardId);
		boardService.updateBoard(board);
		Board updatedBoard = boardService.getBoardById(boardId);
		if (updatedBoard != null) {
			return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{board_id}")
	public ResponseEntity<String> deleteBoard(@PathVariable("board_id") int boardId) {
		boardService.deleteBoard(boardId);
		return new ResponseEntity<>("{\"msg\": \"게시글이 삭제되었습니다.\"}", HttpStatus.OK);
	}
}

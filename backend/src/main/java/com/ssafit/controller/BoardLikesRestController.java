package com.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafit.model.dto.BoardLikes;
import com.ssafit.model.service.BoardLikesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/boardlikes")
@Tag(name = "boardLikesRestController", description = "게시글 좋아요/좋아요 취소 컨트롤러")
public class BoardLikesRestController {

	@Autowired
	private BoardLikesService boardLikesService;

	@PostMapping("/")
	@Operation(summary = "게시글을 좋아요/취소합니다.")
	public ResponseEntity<Void> toggleLike(@RequestBody BoardLikes boardLikes) {
		boardLikesService.toggleLike(boardLikes);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{board_Id}")
	@Operation(summary = "특정 게시글의 좋아요 수를 조회합니다.")
	public ResponseEntity<Integer> getLikesCountByBoardId(@PathVariable("board_Id") int boardId) {
		int likesCount = boardLikesService.getLikesCountByBoardId(boardId);
		return new ResponseEntity<>(likesCount, HttpStatus.OK);
	}
}

package com.ssafit.model.service;

import com.ssafit.model.dto.BoardLikes;

public interface BoardLikesService {
	void toggleLike(BoardLikes boardLikes);

	int getLikesCountByBoardId(int boardId);
}

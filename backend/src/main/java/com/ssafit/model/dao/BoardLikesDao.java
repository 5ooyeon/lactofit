package com.ssafit.model.dao;

import com.ssafit.model.dto.BoardLikes;
import org.apache.ibatis.annotations.Param;

public interface BoardLikesDao {
	void addLike(BoardLikes boardLikes);

	void removeLike(@Param("userId") int userId, @Param("boardId") int boardId);

	BoardLikes getLikeByUsers(@Param("userId") int userId, @Param("boardId") int boardId);

	int getLikesCountByBoardId(int boardId);
}

package com.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.BoardLikesDao;
import com.ssafit.model.dao.BoardDao;
import com.ssafit.model.dto.BoardLikes;
import com.ssafit.model.dto.Board;
import com.ssafit.model.dto.Notification;

@Service
public class BoardLikesServiceImpl implements BoardLikesService {

	@Autowired
	private BoardLikesDao boardLikesDao;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private NotificationService notificationService;

	@Override
	public void toggleLike(BoardLikes boardLikes) {
		BoardLikes existingLike = boardLikesDao.getLikeByUsers(boardLikes.getUserId(), boardLikes.getBoardId());
		if (existingLike != null) {
			boardLikesDao.removeLike(boardLikes.getUserId(), boardLikes.getBoardId());
		} else {
			boardLikesDao.addLike(boardLikes);

			// 게시글 정보 가져오기
			Board board = boardDao.getBoardById(boardLikes.getBoardId());

			// 알림 생성
			Notification notification = new Notification();
			notification.setUserId(board.getUserId());
			notification.setNotificationContent(boardLikes.getUserId() + "가 좋아요 하였습니다. : " + board.getBoardId());
			notification.setNotificationRead(false);
			notificationService.createNotification(notification);
		}
	}

	@Override
	public int getLikesCountByBoardId(int boardId) {
		return boardLikesDao.getLikesCountByBoardId(boardId);
	}
}

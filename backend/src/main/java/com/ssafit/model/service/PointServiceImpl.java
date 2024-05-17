package com.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.PointDao;
import com.ssafit.model.dto.Point;

@Service
public class PointServiceImpl implements PointService {

	@Autowired
	private PointDao pointDao;

	public void trackPoint(Point point) {
		pointDao.trackPoint(point);
	}

	public int getPointsSumByUserId(int user_id) {
		return pointDao.getPointsSumByUserId(user_id);
	}
}

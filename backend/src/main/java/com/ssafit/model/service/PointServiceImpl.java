package com.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafit.model.dao.PointDao;
import com.ssafit.model.dto.Point;

public class PointServiceImpl implements PointService {

	@Autowired
	private PointDao pointDao;

	public void addPoint(Point point) {
		pointDao.addPoint(point);
	}

	public void usePoint(Point point) {
		pointDao.usePoint(point);
	}

	public int getPointsSumByUserId(int user_id) {
		return pointDao.getPointsSumByUserId(user_id);
	}
}

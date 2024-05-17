package com.ssafit.model.service;

import com.ssafit.model.dto.Point;

public interface PointService {

	public void addPoint(Point point);

	public void usePoint(Point point);

	public int getPointsSumByUserId(int user_id);
}

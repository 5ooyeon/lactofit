package com.ssafit.model.service;

import com.ssafit.model.dto.Point;

public interface PointService {

	public void trackPoint(Point point);

	public int getPointsSumByUserId(int user_id);
}

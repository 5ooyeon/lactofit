package com.ssafit.model.dao;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Point;

public interface PointDao {

	void trackPoint(Point point);

	int getPointsSumByUserId(@Param("user_id") int user_id);
}
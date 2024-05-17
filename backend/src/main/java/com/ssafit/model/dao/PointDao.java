package com.ssafit.model.dao;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Point;

public interface PointDao {

	void addPoint(Point point);

	void usePoint(Point point);

	int getPointsSumByUserId(@Param("user_id") int user_id);
}
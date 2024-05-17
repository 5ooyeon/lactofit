package com.ssafit.model.dao;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Routine;

public interface RoutineDao {
	void insertRoutine(@Param("routine") Routine routine);
}
package com.ssafit.model.dao;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

public interface RoutineDao {
	void insertRoutine(Routine routine);

	void insertRoutineComponent(@Param("routineComponent") RoutineComponents routineComponent);

	Integer getLastInsertedRoutineId();
}

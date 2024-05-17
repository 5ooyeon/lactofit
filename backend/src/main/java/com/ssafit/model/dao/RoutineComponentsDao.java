package com.ssafit.model.dao;

import org.apache.ibatis.annotations.Param;

import com.ssafit.model.dto.RoutineComponents;

public interface RoutineComponentsDao {
	void insertRoutineComponents(@Param("routineComponents") RoutineComponents routineComponents);
}
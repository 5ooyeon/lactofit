package com.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafit.model.dto.RoutineComponents;

public interface RoutineComponentsDao {
	// C
	void insertRoutineComponent(RoutineComponents routineComponents);

	// R
	RoutineComponents getRoutineComponentById(int routineComponentsId);

	// U
	void updateRoutineComponent(RoutineComponents routineComponents);

	// D
	void deleteRoutineComponent(int routineComponentsId);

	List<Map<String, Object>> getRoutineComponentsByRoutineId(int routineId);

	List<RoutineComponents> getAllRoutineComponents();
}

package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Routine;

public interface RoutineDao {
	// C
	void insertRoutine(Routine routine);

	// R
	Routine getRoutineById(int routineId);

	// U
	void updateRoutine(Routine routine);

	// D
	void deleteRoutine(int routineId);

	List<Routine> getAllRoutines();
}

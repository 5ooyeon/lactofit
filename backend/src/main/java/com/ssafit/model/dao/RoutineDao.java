package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

public interface RoutineDao {
	void insertRoutine(Routine routine);

	Routine getRoutineById(int routineId);

	void updateRoutine(Routine routine);

	void deleteRoutine(int routineId);

	List<Routine> getAllRoutines();

	List<RoutineComponents> getExercisesByRoutineId(int routineId);
}

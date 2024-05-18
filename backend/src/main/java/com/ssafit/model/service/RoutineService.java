package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Exercise;
import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

public interface RoutineService {

	public Exercise getExerciseById(int exerciseId);

	public List<Exercise> getAllExercises();

	public int createRoutine(Routine routine);

	public void updateRoutine(Routine routine);

	public void deleteRoutine(int routineId);

	public Routine getRoutineById(int routineId);

	public List<Routine> getAllRoutines();

//	public void addExercisesToRoutine(int routineId, List<Exercise> exercises);

	public void updateRoutineComponent(RoutineComponents routineComponents);

	public void deleteRoutineComponent(int routineComponentsId);

	public RoutineComponents getRoutineComponentById(int routineComponentsId);

	public List<RoutineComponents> getRoutineComponentsByRoutineId(int routineId);

	public List<RoutineComponents> getAllRoutineComponents();

//	public List<RoutineComponents> getExercisesByRoutineId(int routineId);
}

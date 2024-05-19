package com.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.model.dao.ExerciseDao;
import com.ssafit.model.dao.RoutineComponentsDao;
import com.ssafit.model.dao.RoutineDao;
import com.ssafit.model.dto.Exercise;
import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

@Service
public class RoutineServiceImpl implements RoutineService {

	@Autowired
	private ExerciseDao exerciseDao;

	@Autowired
	private RoutineDao routineDao;

	@Autowired
	private RoutineComponentsDao routineComponentsDao;

	public Exercise getExerciseById(int exerciseId) {
		return exerciseDao.getExerciseById(exerciseId);
	}

	public List<Exercise> getAllExercises() {
		return exerciseDao.getAllExercises();
	}

	public int createRoutine(Routine routine) {
		routineDao.insertRoutine(routine);
		return routine.getRoutineId();
	}

	public void updateRoutine(Routine routine) {
		routineDao.updateRoutine(routine);
	}

	public void deleteRoutine(int routineId) {
		routineDao.deleteRoutine(routineId);
	}

	public Routine getRoutineById(int routineId) {
		return routineDao.getRoutineById(routineId);
	}

	public List<Routine> getAllRoutines() {
		return routineDao.getAllRoutines();
	}

//	public void addExercisesToRoutine(int routineId, List<Exercise> exercises) {
//		for (Exercise exercise : exercises) {
//			RoutineComponents routineComponent = new RoutineComponents();
//			routineComponent.setExerciseId(exercise.getExerciseId());
//			routineComponent.setRoutineId(routineId);
//			routineComponentsDao.insertRoutineComponent(routineComponent);
//		}
//	}

	public void updateRoutineComponent(RoutineComponents routineComponents) {
		routineComponentsDao.updateRoutineComponent(routineComponents);
	}

	public void deleteRoutineComponent(int routineComponentsId) {
		routineComponentsDao.deleteRoutineComponent(routineComponentsId);
	}

	public RoutineComponents getRoutineComponentById(int routineComponentsId) {
		return routineComponentsDao.getRoutineComponentById(routineComponentsId);
	}

	public List<Map<String, Object>> getRoutineComponentsByRoutineId(int routineId) {
		return routineComponentsDao.getRoutineComponentsByRoutineId(routineId);
	}

	public List<RoutineComponents> getAllRoutineComponents() {
		return routineComponentsDao.getAllRoutineComponents();
	}

	public List<RoutineComponents> getExercisesByRoutineId(int routineId) {
		return routineDao.getExercisesByRoutineId(routineId);
	}

}

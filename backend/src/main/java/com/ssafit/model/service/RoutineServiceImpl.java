package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.model.dao.RoutineComponentsDao;
import com.ssafit.model.dao.RoutineDao;
import com.ssafit.model.dto.Exercise;
import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

@Service
public class RoutineServiceImpl implements RoutineService {

	@Autowired
	private RoutineDao routineDao;

	@Autowired
	private RoutineComponentsDao routineComponentsDao;

	@Transactional
	public int createRoutine(Routine routine) {
		routineDao.insertRoutine(routine);
		return routine.getRoutineId();
	}

	@Transactional
	public void updateRoutine(Routine routine) {
		routineDao.updateRoutine(routine);
	}

	@Transactional
	public void deleteRoutine(int routineId) {
		routineDao.deleteRoutine(routineId);
	}

	@Transactional(readOnly = true)
	public Routine getRoutineById(int routineId) {
		return routineDao.getRoutineById(routineId);
	}

	@Transactional(readOnly = true)
	public List<Routine> getAllRoutines() {
		return routineDao.getAllRoutines();
	}

	@Transactional
	public void addExercisesToRoutine(int routineId, List<Exercise> exercises) {
		for (Exercise exercise : exercises) {
			RoutineComponents routineComponent = new RoutineComponents();
			routineComponent.setExerciseId(exercise.getExerciseId());
			routineComponent.setRoutineId(routineId);
			routineComponentsDao.insertRoutineComponent(routineComponent);
		}
	}

	@Transactional
	public void updateRoutineComponent(RoutineComponents routineComponents) {
		routineComponentsDao.updateRoutineComponent(routineComponents);
	}

	@Transactional
	public void deleteRoutineComponent(int routineComponentsId) {
		routineComponentsDao.deleteRoutineComponent(routineComponentsId);
	}

	@Transactional(readOnly = true)
	public RoutineComponents getRoutineComponentById(int routineComponentsId) {
		return routineComponentsDao.getRoutineComponentById(routineComponentsId);
	}

	@Transactional(readOnly = true)
	public List<RoutineComponents> getRoutineComponentsByRoutineId(int routineId) {
		return routineComponentsDao.getRoutineComponentsByRoutineId(routineId);
	}

	@Transactional(readOnly = true)
	public List<RoutineComponents> getAllRoutineComponents() {
		return routineComponentsDao.getAllRoutineComponents();
	}

}

package com.ssafit.model.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void addExercisesToRoutine(int routineId, List<Exercise> exercises) {
		for (Exercise exercise : exercises) {
			RoutineComponents routineComponent = new RoutineComponents();
			routineComponent.setExerciseId(exercise.getExerciseId());
			routineComponent.setRoutineId(routineId);
			routineComponentsDao.insertRoutineComponent(routineComponent);
		}
	}

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

	public List<List<Map<String, Object>>> getExercisesByRoutineId(int routineId) {
		return routineDao.getExercisesByRoutineId(routineId);
	}

	public List<Map<String, Object>> getRoutinesByUserId(int userId) {
		List<Map<String, Object>> routines = routineDao.getRoutinesByUserId(userId);

		Map<String, Map<String, Object>> groupedRoutines = new LinkedHashMap<>();
		for (Map<String, Object> routine : routines) {
			String routineName = (String) routine.get("routineName");
			if (!groupedRoutines.containsKey(routineName)) {
				Map<String, Object> routineGroup = new LinkedHashMap<>();
				routineGroup.put("routineName", routineName);
				routineGroup.put("routineDesc", routine.get("routineDesc"));
				routineGroup.put("routineId", routine.get("routineId"));
				routineGroup.put("userId", routine.get("userId"));
				routineGroup.put("routineRegDate", routine.get("routineRegDate"));
				routineGroup.put("routineUpdateDate", routine.get("routineUpdateDate"));
				routineGroup.put("exercises", new ArrayList<Map<String, Object>>());
				groupedRoutines.put(routineName, routineGroup);
			}
			Map<String, Object> exercise = new LinkedHashMap<>();
			exercise.put("exerciseName", routine.get("exerciseName"));
			exercise.put("exerciseDesc", routine.get("exerciseDesc"));
			exercise.put("exerciseId", routine.get("exerciseId"));
			exercise.put("routineComponentsId", routine.get("routineComponentsId"));
			exercise.put("exercisePart", routine.get("exercisePart"));
			exercise.put("routineComponentsReps", routine.get("routineComponentsReps"));
			exercise.put("routineComponentsWeight", routine.get("routineComponentsWeight"));

			((List<Map<String, Object>>) groupedRoutines.get(routineName).get("exercises")).add(exercise);
		}

		return new ArrayList<>(groupedRoutines.values());
	}
    public List<Exercise> getExercisesByPart(String part) {
        return exerciseDao.getExercisesByPart(part);
    }

    public List<Exercise> searchExercises(String keyword) {
        return exerciseDao.searchExercises(keyword);
    }

}

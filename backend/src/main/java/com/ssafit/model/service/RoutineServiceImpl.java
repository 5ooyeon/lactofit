package com.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.model.dao.RoutineDao;
import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

@Service
public class RoutineServiceImpl implements RoutineService {

	@Autowired
	private RoutineDao routineDao;

	@Transactional
	public int createRoutine(Routine routine) {
		routineDao.insertRoutine(routine);
		return routineDao.getLastInsertedRoutineId();
	}

	@Transactional
	public void addRoutineComponents(int routineId, List<Map<String, Object>> componentsData) {
		for (Map<String, Object> componentData : componentsData) {
			RoutineComponents component = new RoutineComponents();
			component.setRoutineId(routineId);
			component.setExerciseId((int) componentData.get("exerciseId"));
			component.setRoutineComponentsReps((int) componentData.get("reps"));
			component.setRoutineComponentsWeight((int) componentData.get("weight"));
			routineDao.insertRoutineComponent(component);
		}
	}

}

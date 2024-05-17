package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.model.dao.RoutineComponentsDao;
import com.ssafit.model.dao.RoutineDao;
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
	public void addRoutineComponents(int routineId, List<RoutineComponents> components) {
		for (RoutineComponents component : components) {
			component.setRoutineId(routineId);
			routineComponentsDao.insertRoutineComponents(component);
		}
	}
}

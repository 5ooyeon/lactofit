package com.ssafit.model.service;

import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

import java.util.List;

public interface RoutineService {
	public int createRoutine(Routine routine);

	public void addRoutineComponents(int routineId, List<RoutineComponents> components);
}

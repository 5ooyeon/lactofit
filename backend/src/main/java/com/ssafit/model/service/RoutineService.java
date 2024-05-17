package com.ssafit.model.service;

import java.util.List;
import java.util.Map;

import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;

public interface RoutineService {
	public int createRoutine(Routine routine);

	public void addRoutineComponents(int routineId, List<Map<String, Object>> components);
}

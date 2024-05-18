package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssafit.model.dto.Exercise;
import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;
import com.ssafit.model.service.RoutineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/routines")
@Tag(name = "routineRestController", description = "루틴 컨트롤러")
public class RoutineRestController {

	@Autowired
	private RoutineService routineService;

	@PostMapping("/")
	@Operation(summary = "루틴을 등록합니다.")
	public int createRoutine(@RequestBody Routine routine) {
		return routineService.createRoutine(routine);
	}

	@PutMapping("/")
	@Operation(summary = "루틴을 수정합니다.")
	public void updateRoutine(@RequestBody Routine routine) {
		routineService.updateRoutine(routine);
	}

	@DeleteMapping("/{routine_Id}")
	@Operation(summary = "루틴을 삭제합니다.")
	public void deleteRoutine(@PathVariable("routine_Id") int routineId) {
		routineService.deleteRoutine(routineId);
	}

	@GetMapping("/{routine_Id}")
	@Operation(summary = "루틴을 조회합니다.")
	public Routine getRoutineById(@PathVariable("routine_Id") int routineId) {
		return routineService.getRoutineById(routineId);
	}

	@GetMapping("/")
	@Operation(summary = "모든 루틴을 조회합니다.")
	public List<Routine> getAllRoutines() {
		return routineService.getAllRoutines();
	}

	@PostMapping("/exercises/{routine_Id}")
	@Operation(summary = "루틴에 운동을 추가합니다.")
	public void addExercisesToRoutine(@PathVariable("routine_Id") int routineId,
			@RequestBody List<Exercise> exercises) {
		routineService.addExercisesToRoutine(routineId, exercises);
	}

	@PutMapping("/routinecomponent")
	@Operation(summary = "루틴 컴포넌트를 수정합니다.")
	public void updateRoutineComponent(@RequestBody RoutineComponents routineComponents) {
		routineService.updateRoutineComponent(routineComponents);
	}

	@DeleteMapping("/routinecomponent/{routineComponents_Id}")
	@Operation(summary = "루틴 컴포넌트를 삭제합니다.")
	public void deleteRoutineComponent(@PathVariable("routineComponents_Id") int routineComponentsId) {
		routineService.deleteRoutineComponent(routineComponentsId);
	}

	@GetMapping("/routinecomponent/{routineComponents_Id}")
	@Operation(summary = "루틴 컴포넌트를 조회합니다.")
	public RoutineComponents getRoutineComponentById(@PathVariable("routineComponents_Id") int routineComponentsId) {
		return routineService.getRoutineComponentById(routineComponentsId);
	}

	@GetMapping("/routinecomponents/{routine_Id}")
	@Operation(summary = "루틴 ID로 루틴 컴포넌트를 조회합니다.")
	public List<RoutineComponents> getRoutineComponentsByRoutineId(@PathVariable("routine_Id") int routineId) {
		return routineService.getRoutineComponentsByRoutineId(routineId);
	}

	@GetMapping("/routinecomponents/all")
	@Operation(summary = "모든 루틴 컴포넌트를 조회합니다.")
	public List<RoutineComponents> getAllRoutineComponents() {
		return routineService.getAllRoutineComponents();
	}
}

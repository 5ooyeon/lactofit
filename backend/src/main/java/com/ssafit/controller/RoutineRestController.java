package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine) {
		int id = routineService.createRoutine(routine);
		routine.setRoutineId(id);
		return new ResponseEntity<>(routine, HttpStatus.CREATED);
	}

	@PutMapping("/")
	@Operation(summary = "루틴을 수정합니다.")
	public ResponseEntity<Void> updateRoutine(@RequestBody Routine routine) {
		routineService.updateRoutine(routine);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{routine_Id}")
	@Operation(summary = "루틴을 삭제합니다.")
	public ResponseEntity<Void> deleteRoutine(@PathVariable("routine_Id") int routineId) {
		routineService.deleteRoutine(routineId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{routine_Id}")
	@Operation(summary = "루틴을 조회합니다.")
	public ResponseEntity<Routine> getRoutineById(@PathVariable("routine_Id") int routineId) {
		Routine routine = routineService.getRoutineById(routineId);
		if (routine != null) {
			return new ResponseEntity<>(routine, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/")
	@Operation(summary = "모든 루틴을 조회합니다.")
	public ResponseEntity<List<Routine>> getAllRoutines() {
		List<Routine> routines = routineService.getAllRoutines();
		return new ResponseEntity<>(routines, HttpStatus.OK);
	}

	@GetMapping("/exercises")
	@Operation(summary = "모든 운동을 조회합니다.")
	public ResponseEntity<List<Exercise>> getAllExercises() {
		List<Exercise> exercises = routineService.getAllExercises();
		return new ResponseEntity<>(exercises, HttpStatus.OK);
	}

	@PostMapping("/exercises/{routine_Id}")
	@Operation(summary = "루틴에 운동을 추가합니다.")
	public ResponseEntity<Void> addExercisesToRoutine(@PathVariable("routine_Id") int routineId,
			@RequestBody List<Exercise> exercises) {
		routineService.addExercisesToRoutine(routineId, exercises);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/routinecomponents")
	@Operation(summary = "루틴 컴포넌트를 수정합니다.")
	public ResponseEntity<Void> updateRoutineComponent(@RequestBody RoutineComponents routineComponents) {
		routineService.updateRoutineComponent(routineComponents);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/routinecomponents/{routineComponents_Id}")
	@Operation(summary = "루틴 컴포넌트를 삭제합니다.")
	public ResponseEntity<Void> deleteRoutineComponent(@PathVariable("routineComponents_Id") int routineComponentsId) {
		routineService.deleteRoutineComponent(routineComponentsId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/routinecomponents/{routineComponents_Id}")
	@Operation(summary = "루틴 컴포넌트를 조회합니다.")
	public ResponseEntity<RoutineComponents> getRoutineComponentById(
			@PathVariable("routineComponents_Id") int routineComponentsId) {
		RoutineComponents routineComponents = routineService.getRoutineComponentById(routineComponentsId);
		if (routineComponents != null) {
			return new ResponseEntity<>(routineComponents, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/routinecomponents/{routine_Id}")
	@Operation(summary = "루틴 ID로 루틴 컴포넌트를 조회합니다.")
	public ResponseEntity<List<RoutineComponents>> getRoutineComponentsByRoutineId(
			@PathVariable("routine_Id") int routineId) {
		List<RoutineComponents> routineComponents = routineService.getRoutineComponentsByRoutineId(routineId);
		return new ResponseEntity<>(routineComponents, HttpStatus.OK);
	}

	@GetMapping("/routinecomponents")
	@Operation(summary = "모든 루틴 컴포넌트를 조회합니다.")
	public ResponseEntity<List<RoutineComponents>> getAllRoutineComponents() {
		List<RoutineComponents> routineComponents = routineService.getAllRoutineComponents();
		return new ResponseEntity<>(routineComponents, HttpStatus.OK);
	}
}

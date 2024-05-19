package com.ssafit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@PutMapping("/{routine_Id}")
	@Operation(summary = "루틴을 수정합니다.")
	public ResponseEntity<Routine> updateRoutine(@PathVariable("routine_Id") int routineId,
			@RequestBody Routine routine) {
		routine.setRoutineId(routineId);
		routineService.updateRoutine(routine);
		Routine updatedRoutine = routineService.getRoutineById(routineId);
		if (updatedRoutine != null) {
			return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{routine_Id}")
	@Operation(summary = "루틴을 삭제합니다.")
	public ResponseEntity<Void> deleteRoutine(@PathVariable("routine_Id") int routineId) {
		routineService.deleteRoutine(routineId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{routine_Id}")
	@Operation(summary = "루틴(이름, 설명 등)을 조회합니다.")
	public ResponseEntity<Routine> getRoutineById(@PathVariable("routine_Id") int routineId) {
		Routine routine = routineService.getRoutineById(routineId);
		if (routine != null) {
			return new ResponseEntity<>(routine, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{routine_Id}/exercises")
	@Operation(summary = "루틴(운동)을 조회합니다.")
	public ResponseEntity<?> getExercisesByRoutineId(@PathVariable("routine_Id") int routineId) {
		List<List<Map<String, Object>>> exercises = routineService.getExercisesByRoutineId(routineId);
		if (exercises == null || exercises.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(exercises, HttpStatus.OK);
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

	@PutMapping("/routinecomponents/{routineComponents_Id}")
	@Operation(summary = "루틴 컴포넌트를 수정합니다.")
	public ResponseEntity<RoutineComponents> updateRoutineComponent(
			@PathVariable("routineComponents_Id") int routineComponentsId,
			@RequestBody RoutineComponents routineComponents) {
		routineComponents.setRoutineComponentsId(routineComponentsId);
		routineService.updateRoutineComponent(routineComponents);
		RoutineComponents updated = routineService.getRoutineComponentById(routineComponentsId);

		if (updated != null) {
			return new ResponseEntity<>(updated, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

	@GetMapping("/routinecomponents/routine/{routine_Id}")
	@Operation(summary = "루틴 ID로 루틴 컴포넌트를 조회합니다.")
	public ResponseEntity<Map<String, Object>> getRoutineComponentsByRoutineId(
			@PathVariable("routine_Id") int routineId) {
		List<Map<String, Object>> routineComponents = routineService.getRoutineComponentsByRoutineId(routineId);
		return new ResponseEntity(routineComponents, HttpStatus.OK);
	}

	@GetMapping("/routinecomponents")
	@Operation(summary = "모든 루틴 컴포넌트를 조회합니다.")
	public ResponseEntity<List<RoutineComponents>> getAllRoutineComponents() {
		List<RoutineComponents> routineComponents = routineService.getAllRoutineComponents();
		return new ResponseEntity<>(routineComponents, HttpStatus.OK);
	}

	@GetMapping("/users/{user_Id}")
	@Operation(summary = "유저ID로 루틴을 조회합니다.")
	public ResponseEntity<List<Map<String, Object>>> getRoutinesByUserId(@PathVariable("user_Id") int userId) {
		List<Map<String, Object>> routines = routineService.getRoutinesByUserId(userId);
		if (routines == null || routines.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(routines, HttpStatus.OK);
	}

	@GetMapping("/exercises/search/{exercise_part}")
	@Operation(summary = "특정 운동 부위의 운동만 반환합니다.")
	public ResponseEntity<List<Exercise>> getExercisesByPart(@PathVariable("exercise_part") String exercisePart) {
		List<Exercise> exercises = routineService.getExercisesByPart(exercisePart);
		if (exercises == null || exercises.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(exercises, HttpStatus.OK);
	}

	@GetMapping("/exercises/search")
	@Operation(summary = "운동명 또는 운동 부위에 포함된 운동을 반환합니다.")
	public ResponseEntity<List<Exercise>> searchExercises(@RequestParam("keyword") String keyword) {
		List<Exercise> exercises = routineService.searchExercises(keyword);
		if (exercises == null || exercises.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(exercises, HttpStatus.OK);
	}

}

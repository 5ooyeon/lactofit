package com.ssafit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Routine;
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
	@Operation(summary = "루틴을 생성합니다.")
	public ResponseEntity<Integer> createRoutine(@RequestBody Routine routine) {
		int routineId = routineService.createRoutine(routine);
		return new ResponseEntity<>(routineId, HttpStatus.CREATED);
	}

	@PostMapping("/components")
	@Operation(summary = "루틴 컴포넌츠를 생성합니다.")
	public ResponseEntity<Void> addRoutineComponents(@RequestBody Map<String, Object> payload) {
		int routineId = (int) payload.get("routineId");
		List<Map<String, Object>> componentsData = (List<Map<String, Object>>) payload.get("components");

		routineService.addRoutineComponents(routineId, componentsData);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}

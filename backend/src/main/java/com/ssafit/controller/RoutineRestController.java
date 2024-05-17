package com.ssafit.controller;

import com.ssafit.model.dto.Routine;
import com.ssafit.model.dto.RoutineComponents;
import com.ssafit.model.service.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routines")
@Tag(name = "routineRestController", description = "루틴 컨트롤러")
public class RoutineRestController {

	@Autowired
	private RoutineService routineService;

	@PostMapping("/")
	@Operation(summary = "루틴을 생성합니다.")
	public int createRoutine(@RequestBody Routine routine) {
		return routineService.createRoutine(routine);
	}

	@PostMapping("/{routineId}/components")
	@Operation(summary = "루틴컴포넌츠를 생성합니다.")
	public void addRoutineComponents(@PathVariable int routineId, @RequestBody List<RoutineComponents> components) {
		routineService.addRoutineComponents(routineId, components);
	}
}

package com.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.Point;
import com.ssafit.model.service.PointService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/points")
@Tag(name = "pointRestController", description = "포인트 획득/사용/총량 ")
public class PointRestController {

	@Autowired
	private PointService pointService;

	@PostMapping("/")
	@Operation(summary = "포인트를 획득을 기록합니다.")
	public void addPoint(@RequestBody Point point) {
		pointService.addPoint(point);
	}

	@PostMapping("/use")
	@Operation(summary = "포인트를 사용을 기록합니다.")
	public void usePoint(@RequestBody Point point) {
		pointService.usePoint(point);
	}

	@GetMapping("/{user_id}")
	@Operation(summary = "포인트를 총량을 출력합니다.")
	public int getPointsSumByUserId(@PathVariable int user_id) {
		return pointService.getPointsSumByUserId(user_id);
	}
}

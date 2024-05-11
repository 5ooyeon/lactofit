package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.SearchCondition;
import com.ssafit.model.dto.VideoDto;
import com.ssafit.model.service.VideoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api-video")
public class VideoController {
	private final VideoService videoService;
	
	@Autowired
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	@GetMapping("/video")
	@Operation(summary = "영상 조회", description = "검색 조건에 따라 조회가능합니다 (전체 조회도 가능)")
	public ResponseEntity<?> list(@ModelAttribute SearchCondition condition) {
		List<VideoDto> videoList = videoService.getVideoList(condition);
		
		if (videoList == null || videoList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<VideoDto>>(videoList, HttpStatus.OK);
	}
	
	//영상 등록
	@PostMapping("/video")
	@Operation(summary = "영상 등록", description = "영상을 등록합니다")
	public ResponseEntity<Void> insert(@RequestBody VideoDto video){
		videoService.insertVideo(video);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/video/{id}")
	@Operation(summary = "영상 수정", description = "영상을 수정합니다")
	public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody VideoDto video){
		video.setVideoSeq(id);
		videoService.updateVideo(video);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/video/{id}")
	@Operation(summary = "영상 삭제", description = "영상을 삭제합니다")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		videoService.deleteVideo(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

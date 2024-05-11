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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.model.dto.ReviewDto;
import com.ssafit.model.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	private final ReviewService reviewService;
	
	@Autowired
	ReviewController(ReviewService reviewService){
		this.reviewService = reviewService;
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "영상 리뷰 조회", description = "검색 조건은 비디오 번호입니다.")
	public ResponseEntity<?> getReviewList(@PathVariable("id") int videoSeq) {
		List<ReviewDto> reviewList = reviewService.getReviewList(videoSeq);
		boolean notFound = reviewList.size() == 0;
		return new ResponseEntity<>(reviewList, notFound ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	@Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다.")
	public ResponseEntity<?> insertReview(@ModelAttribute ReviewDto review, @PathVariable("id") int id){
		review.setVideoSeq(id);
		reviewService.insertReview(review);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.")
	public ResponseEntity<?> deleteReview(@PathVariable("id") int reviewSeq){
		int res = reviewService.deleteReview(reviewSeq);
		
		//삭제가 되지 않았다면 not found
		return new ResponseEntity<>(res==1 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다.")
	public ResponseEntity<?> updateReview(@ModelAttribute ReviewDto review ,@PathVariable("id") int reviewSeq){
		review.setReviewSeq(reviewSeq);
		int res = reviewService.updateReview(review);
		
		//수정이 되지 않았다면 not found
		return new ResponseEntity<>(res==1 ? HttpStatus.OK : HttpStatus.NOT_FOUND); 
	}
	
	
	
	
	
	
	
	
}

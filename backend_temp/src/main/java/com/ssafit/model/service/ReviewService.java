package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.ReviewDto;

public interface ReviewService {

	List<ReviewDto> getReviewList(int id);

	void insertReview(ReviewDto review);

	int deleteReview(int reviewSeq);

	int updateReview(ReviewDto review);


}

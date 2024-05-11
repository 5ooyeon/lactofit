package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.ReviewDto;
import com.ssafit.model.dto.VideoDto;

public interface ReviewDao {

	List<ReviewDto> getReviewList(int videoSeq);

	int updateReview(ReviewDto review);

	int deleteReview(int review_seq);

	int insertReview(ReviewDto review);
}

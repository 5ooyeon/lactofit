package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.ReviewDao;
import com.ssafit.model.dto.ReviewDto;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewDao reviewDao;
	
	@Autowired
	ReviewServiceImpl(ReviewDao reviewDao){
		this.reviewDao = reviewDao;
	}

	@Override
	public List<ReviewDto> getReviewList(int videoSeq) {
		return reviewDao.getReviewList(videoSeq);
	}

	@Override
	public void insertReview(ReviewDto review) {
		reviewDao.insertReview(review);
	}

	@Override
	public int deleteReview(int reviewSeq) {
		return reviewDao.deleteReview(reviewSeq);
	}

	@Override
	public int updateReview(ReviewDto review) {
		// TODO Auto-generated method stub
		return reviewDao.updateReview(review);
	}

}

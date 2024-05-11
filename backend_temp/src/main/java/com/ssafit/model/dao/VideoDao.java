package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.SearchCondition;
import com.ssafit.model.dto.VideoDto;

public interface VideoDao {
	
		// 전체 영상 조회
		public List<VideoDto> searchVideo(SearchCondition con);

		// 영상 등록
		public void insertVideo(VideoDto video);

		// 영상 수정
		public void updateVideo(VideoDto video);

		// 영상 삭제
		public void deleteVideo(int id);

}

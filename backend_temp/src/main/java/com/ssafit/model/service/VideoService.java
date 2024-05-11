package com.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafit.model.dto.SearchCondition;
import com.ssafit.model.dto.VideoDto;

@Service
public interface VideoService {
	//검색 및 조회
			public List<VideoDto> getVideoList(SearchCondition con);
			
			//영상 등록
			public void insertVideo(VideoDto video);
			
			//영상 수정
			public void updateVideo(VideoDto video);
			
			//영상 삭제
			public void deleteVideo(int id);
}

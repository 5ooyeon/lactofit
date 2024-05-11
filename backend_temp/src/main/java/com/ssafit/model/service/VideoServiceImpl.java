package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.VideoDao;
import com.ssafit.model.dto.SearchCondition;
import com.ssafit.model.dto.VideoDto;

@Service
public class VideoServiceImpl implements VideoService {
	
	
	private final VideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	public List<VideoDto> getVideoList(SearchCondition con) {
		return videoDao.searchVideo(con);
	}

	@Override
	public void insertVideo(VideoDto video) {
		videoDao.insertVideo(video);
	}

	@Override
	public void updateVideo(VideoDto video) {
		videoDao.updateVideo(video);
	}

	@Override
	public void deleteVideo(int id) {
		videoDao.deleteVideo(id);
	}

}

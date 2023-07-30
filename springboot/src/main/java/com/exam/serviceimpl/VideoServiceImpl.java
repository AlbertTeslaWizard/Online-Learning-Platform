package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Video;
import com.exam.mapper.VideoMapper;
import com.exam.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public IPage<Video> findAll(Page<Video> page) {
        return videoMapper.findAll(page);
    }

    @Override
    public Video findById(Integer videoId) {
        return videoMapper.findById(videoId);
    }

    @Override
    public int deleteById(Integer videoId) {
        return videoMapper.deleteById(videoId);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int add(Video video) {
        return videoMapper.add(video);
    }
}

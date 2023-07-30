package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Video;

public interface VideoService {
    IPage<Video> findAll(Page<Video> page);

    public Video findById(Integer videoId);

    public int deleteById(Integer videoId);

    public int update(Video video);

    public int add(Video video);
}

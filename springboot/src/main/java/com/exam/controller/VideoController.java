package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.Video;
import com.exam.serviceimpl.VideoServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class VideoController {
    private VideoServiceImpl videoService;
    @Autowired
    public VideoController(VideoServiceImpl videoService){
        this.videoService = videoService;
    }

    @GetMapping("/videos/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size){
        Page<Video> videoPage = new Page<>(page,size);
        IPage<Video> videoIPage = videoService.findAll(videoPage);

        return ApiResultHandler.buildApiResult(200,"查询所有视频", videoIPage);
    }

    @GetMapping("/video/{videoId}")
    public ApiResult findById(@PathVariable("videoId") Integer videoId){
        return ApiResultHandler.success(videoService.findById(videoId));
    }

    @DeleteMapping("/video/{videoId}")
    public ApiResult deleteById(@PathVariable("videoId") Integer videoId){
        return ApiResultHandler.success(videoService.deleteById(videoId));
    }

    @PutMapping("/video")
    public ApiResult update(@RequestBody Video video){
        return ApiResultHandler.success(videoService.update(video));
    }

    @PostMapping("/video")
    public ApiResult add(@RequestBody Video video){
        return ApiResultHandler.success(videoService.add(video));
    }
}

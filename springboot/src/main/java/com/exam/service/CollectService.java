package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Collect;

import java.util.List;

public interface CollectService {
    List<Collect> findAll();

    List<Collect> findById(Integer paperId);

    int add(Collect collect);
}

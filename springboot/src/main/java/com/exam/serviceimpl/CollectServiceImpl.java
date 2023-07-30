package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Collect;
import com.exam.mapper.CollectMapper;
import com.exam.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Override
    public List<Collect> findAll() {
        return collectMapper.findAll();
    }

    @Override
    public List<Collect> findById(Integer paperId) {
        return collectMapper.findById(paperId);
    }

    @Override
    public int add(Collect collect) {
        return collectMapper.add(collect);
    }
}

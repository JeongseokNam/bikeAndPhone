package com.kong.bike.config.pageView.Impl;

import com.kong.bike.config.pageView.PageViewService;
import com.kong.bike.config.pageView.repository.PageViewRepository;
import com.kong.bike.entity.PageViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageViewServiceImpl implements PageViewService {

    @Autowired
    private PageViewRepository pageViewRepository;

    @Override
    public void save(PageViewEntity pageViewEntity) {
        pageViewRepository.save(pageViewEntity);
    }
}

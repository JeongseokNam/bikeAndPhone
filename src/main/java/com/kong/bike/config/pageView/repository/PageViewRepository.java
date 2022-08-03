package com.kong.bike.config.pageView.repository;

import com.kong.bike.entity.PageViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageViewRepository extends JpaRepository<PageViewEntity,Long> {
}

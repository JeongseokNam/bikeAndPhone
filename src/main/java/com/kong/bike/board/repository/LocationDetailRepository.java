package com.kong.bike.board.repository;

import com.kong.bike.entity.LocationDetailEntity;
import com.kong.bike.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDetailRepository extends JpaRepository<LocationDetailEntity,Long> {
    List<LocationDetailEntity> findByLocationEntity(LocationEntity locationEntity);
}

package com.kong.bike.board.repository;

import com.kong.bike.entity.BikeBrandEntity;
import com.kong.bike.entity.BikeModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeModelRepository extends JpaRepository<BikeModelEntity,Long> {

    List<BikeModelEntity> findByBikeBrandEntity(BikeBrandEntity bikeBrandEntity);
}

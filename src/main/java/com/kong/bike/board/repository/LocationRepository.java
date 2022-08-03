package com.kong.bike.board.repository;

import com.kong.bike.entity.BikeBrandEntity;
import com.kong.bike.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity,Long> {
    LocationEntity findByLocationName(String locationName);
}

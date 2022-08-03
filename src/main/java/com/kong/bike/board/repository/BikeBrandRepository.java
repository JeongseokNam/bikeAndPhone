package com.kong.bike.board.repository;

import com.kong.bike.entity.BikeBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeBrandRepository extends JpaRepository<BikeBrandEntity,Long> {
    BikeBrandEntity findByBikeBrand(String bikeBrand);
}

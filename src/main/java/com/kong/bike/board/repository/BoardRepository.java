package com.kong.bike.board.repository;

import com.kong.bike.entity.BikeBoardEntity;
import com.kong.bike.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BikeBoardEntity,Long>{
    @Query(value = "select board_id from(select row_number() over(order by board_id desc) as num,board_id from bike_board_entity)t where num= 1",nativeQuery = true)
    Integer getRecentBoardId();

    Page<BikeBoardEntity> findAllByDelYnOrderByModifiedDateDesc(String delYn, Pageable pageable);

    BikeBoardEntity findByBoardId(Long boardId);


    List<BikeBoardEntity> findByDelYn(String n);

    Page<BikeBoardEntity> findByDelYnAndMemberEntity(String delYn, MemberEntity memberEntity, Pageable pageable);
}

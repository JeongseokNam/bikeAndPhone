package com.kong.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LocationDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationDetailId; //상세지역 id
    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity;

    @Column
    private String locationDetailName;//상세지역 name
}

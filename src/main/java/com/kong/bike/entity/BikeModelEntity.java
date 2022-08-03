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
public class BikeModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId; //모델 id

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BikeBrandEntity bikeBrandEntity;

    @Column
    private String bikeModel;//모델 name
}

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
public class BikeBrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId; //브랜드 id

    @Column
    private String bikeBrand;//브랜드name

}


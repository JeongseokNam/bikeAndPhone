package com.kong.bike.DTO;

import lombok.Data;

@Data
public class SearchDTO {

    private String keyword;

    private String bikeBrand;

    private String location;

    private String bikeYear;

    private int driveKm =0;

    private String bikeModel;

    private int kmMax;

    private int kmMin;

    private String searchType;

    private String soleOrCorporate;
}

package com.grig.edu.shawermacloud.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShawermaOrder {
    private String deliveryName;
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryState;
    private String deliveryZip;

    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Shawerma> shawaList = new ArrayList<>();

    public void addShawa(Shawerma shawa) {
        shawaList.add(shawa);
    }
}

package com.oto.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auto extends AEntity {
    @Override
    public String getTableName() {
        return "auto";
    }

    @Override
    public String getForeignKeyName() {
        return "";
    }

    private String name;
    // TODO: make brand an entity and reference it here with foreign keu
    private String brand;
    private String model;
    private String year;
    private String vclass;
    private String vtype;
    private Long mileage;

    private Long pricePerHour;
}

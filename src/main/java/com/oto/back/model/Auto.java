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
    private String brand;
    private String model;
    private String year;
    private String vClass;
    private String vType;
    private long mileage;
}

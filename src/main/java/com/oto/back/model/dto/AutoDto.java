package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoDto extends ADto {
    private String name;
    private String brand;
    private String model;
    private String year;
    private String vClass;
    private String vType;
    private long mileage;

    @Override
    boolean validate() {
        return false;
    }
    @Override
    boolean validate(String validationType) {
        return false;
    }
}

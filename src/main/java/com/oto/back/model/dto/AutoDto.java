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
    private String vclass;
    private String vtype;
    private Long mileage;

    private Long pricePerHour;
    @Override
    public boolean validate() {
        return false;
    }
    @Override
    public boolean validate(String validationType) {
        return false;
    }
}

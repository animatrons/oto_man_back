package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RideDto extends ADto {

    private Date start;
    private Date checkIn;
    private RiderDto rider; // map to riderId in entity
    private AutoDto auto; // map to autoId in entity
    private String comment;
    private long miles;

    @Override
    boolean validate() {
        return false;
    }
    @Override
    boolean validate(String validationType) {
        return false;
    }
}

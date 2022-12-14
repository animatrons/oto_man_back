package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
public class RideDto extends ADto {

    private OffsetDateTime start;
    private OffsetDateTime checkIn;
    private RiderDto rider; // map to riderId in entity
    private AutoDto auto; // map to autoId in entity
    private String comment;
    private Long miles;

    @Override
    public boolean validate() {
        return false;
    }
    @Override
    public boolean validate(String validationType) {
        return false;
    }
}

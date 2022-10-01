package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
public class RiderDto extends ADto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private OffsetDateTime birthDate;

    @Override
    public boolean validate() {
        return false;
    }
    @Override
    public boolean validate(String validationType) {
        return false;
    }
}

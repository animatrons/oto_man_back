package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RiderDto extends ADto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date birthDate;

    @Override
    boolean validate() {
        return false;
    }
    @Override
    boolean validate(String validationType) {
        return false;
    }
}

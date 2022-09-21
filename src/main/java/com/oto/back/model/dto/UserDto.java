package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends ADto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    @Override
    boolean validate() {
        return false;
    }
    @Override
    boolean validate(String validationType) {
        return false;
    }
}

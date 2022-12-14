package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto extends ADto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<String> role;

    @Override
    public boolean validate() {
        return false;
    }
    @Override
    public boolean validate(String validationType) {
        return false;
    }
}

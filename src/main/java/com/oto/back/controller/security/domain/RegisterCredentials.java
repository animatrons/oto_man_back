package com.oto.back.controller.security.domain;

import com.oto.back.model.dto.ADto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCredentials extends ADto {
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

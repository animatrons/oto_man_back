package com.oto.back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto extends ADto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> role;

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean validate(String validationType) {
        return true;
    }
}

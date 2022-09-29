package com.oto.back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto extends ADto {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String[] role;

    @Override
    boolean validate() {
        return true;
    }

    @Override
    boolean validate(String validationType) {
        return true;
    }
}

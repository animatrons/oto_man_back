package com.oto.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AEntity {
    @Override
    public String getTableName() {
        return "user_";
    }

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}

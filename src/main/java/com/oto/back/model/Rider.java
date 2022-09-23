package com.oto.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rider extends AEntity {
    @Override
    public String getTableName() {
        return "rider";
    }

    @Override
    public String getForeignKeyName() {
        return "";
    }

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date birthDate;
}

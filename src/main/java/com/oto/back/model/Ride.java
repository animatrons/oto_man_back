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
public class Ride extends AEntity {
    @Override
    public String getTableName() {
        return "ride";
    }

    @Override
    public String getForeignKeyName() {
        return "";
    }

    private Date start;
    private Date checkIn;
    private String riderId; // foreign key of Rider table
    private String autoId; // foreign key of Auto table
    private String comment;
    private long miles;
}

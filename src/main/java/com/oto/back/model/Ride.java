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

    private Date start;
    private Date checkIn;
    private String riderId;
    private String autoId;
    private String comment;
    private long miles;
}

package com.oto.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
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

    private OffsetDateTime start;
    private OffsetDateTime checkIn;
    private Integer riderId; // foreign key of Rider table
    private Integer autoId; // foreign key of Auto table
    private String comment;
    private Long miles;
}

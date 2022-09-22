package com.oto.back.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class AEntity {

    private String id;
    private Date createdAt;
    private Date updatedAt;
    private String code;
    private String label;

    public abstract String getTableName();
}

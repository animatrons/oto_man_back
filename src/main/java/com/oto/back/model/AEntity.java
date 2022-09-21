package com.oto.back.model;

import java.util.Date;

public abstract class AEntity {

    private Date createdAt;
    private Date updatedAt;
    private String code;
    private String label;

    public abstract String getTableName();
}

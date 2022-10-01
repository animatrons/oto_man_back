package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ADto {

    private Integer id;
    public abstract boolean validate();
    public abstract boolean validate(String validationType);
}

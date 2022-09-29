package com.oto.back.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ADto {

    private Integer id;
    abstract boolean validate();
    abstract boolean validate(String validationType);
}

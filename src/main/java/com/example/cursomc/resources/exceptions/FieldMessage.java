package com.example.cursomc.resources.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;


}

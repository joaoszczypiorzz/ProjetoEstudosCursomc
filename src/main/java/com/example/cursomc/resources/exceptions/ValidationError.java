package com.example.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandardError{
    private static final long serialVerisonUID = 1L;

    private List<FieldMessage> Errors = new ArrayList<>();

    public ValidationError(Integer status, String mensagem, long timeStamp) {
        super(status, mensagem, timeStamp);

    }

    public List<FieldMessage> getErrors(){
        return Errors;
    }

    public void addError(String fieldName, String mensagem){
        Errors.add(new FieldMessage(fieldName, mensagem));
    }
}

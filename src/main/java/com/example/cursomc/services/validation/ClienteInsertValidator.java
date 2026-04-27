package com.example.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import com.example.cursomc.Enums.TipoCliente;
import com.example.cursomc.dto.ClienteNewDto;
import com.example.cursomc.resources.exceptions.FieldMessage;
import com.example.cursomc.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
        }
        else if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido!"));
        }

        //For para adicionar as mensagens personalizadas nas mensagens do Framework
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}

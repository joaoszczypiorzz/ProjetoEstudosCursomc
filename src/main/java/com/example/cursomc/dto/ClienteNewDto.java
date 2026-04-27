package com.example.cursomc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ClienteNewDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;

    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;
}

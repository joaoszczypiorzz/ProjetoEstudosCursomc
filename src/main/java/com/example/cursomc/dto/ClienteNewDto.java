package com.example.cursomc.dto;

import com.example.cursomc.services.validation.ClienteInsert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ClienteInsert
public class ClienteNewDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 Caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail Inválido!")
    private String email;
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cpfOuCnpj;

    private Integer tipo;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String logadouro;
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String numero;

    private String complemento;
    private String bairro;
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cep;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;
}

package com.example.cursomc.dto;

import com.example.cursomc.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * Esta seria uma Classe Dto ( Data Transfer Object ), que basicamente define quais dados nós queremos que sejam
 * Exibidos durante uma operação que solicita Dados desta classe, que neste caso é a Categoria
 */
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CategoriaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDto(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }

}

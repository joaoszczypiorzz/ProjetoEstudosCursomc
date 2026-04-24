package com.example.cursomc.domain;

import com.example.cursomc.Enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor //Gera o Contrutor vazio automaticamente, para mapeamento do Hibernate
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Preparando Equals and Hash code
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include //Diz ao Lombok usar apenas este atributo para gerar o Hash Code e Equals
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    //Bloqueando o Lombok de gerar os Getter e Setters para esse Atributo
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer tipo;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos =  new ArrayList<>(); //Cliente possui vários endereços

    @ElementCollection
    @CollectionTable(name="TELEFONE")
    private Set<String> telefones = new HashSet<>(); //representando os telefones sendo um Conjunto de Strings

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();


    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, Integer tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }
    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }
}

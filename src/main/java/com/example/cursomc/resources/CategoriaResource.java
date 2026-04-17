package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //definindo que esta classe será um controlador Rest
@RequestMapping(value = "/categorias") //definindo qual o endpoint padrão para essa classe
public class CategoriaResource {

@RequestMapping(method = RequestMethod.GET) //definindo que essa requisição será um request Get
    public List<Categoria> listar(){
        Categoria cat1 = new Categoria(1,"Informática");
        Categoria cat2 = new Categoria(2,"Escritório");

        List<Categoria> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);

        return lista;
    }
}

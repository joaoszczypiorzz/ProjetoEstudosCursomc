package com.example.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //definindo que esta classe será um controlador Rest
@RequestMapping(value = "/categorias") //definindo qual o endpoint padrão para essa classe
public class CategoriaResource {

@RequestMapping(method = RequestMethod.GET) //definindo que essa requisição será um request Get
    public String listar(){
        return "Minha Rest está funcionando!";
    }
}

package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //definindo que esta classe será um controlador Rest
@RequestMapping(value = "/categorias") //definindo qual o endpoint padrão para essa classe
public class CategoriaResource {
    @Autowired
    private CategoriaService service; //com autoWired para ele instanciar automaticamente este obj

    @RequestMapping(value="/{id}", method = RequestMethod.GET) //definindo que essa requisição será um request Get
    public ResponseEntity<?> find(@PathVariable Integer id){
        Categoria obj = service.buscar(id); //acessando o Service
        return ResponseEntity.ok().body(obj);
    }
}

package com.example.cursomc.resources;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //definindo que esta classe será um controlador Rest
@RequestMapping(value = "/clientes") //definindo qual o endpoint padrão para essa classe
public class ClienteResource {
    @Autowired
    private ClienteService service; //com autoWired para ele instanciar automaticamente este obj

    @RequestMapping(value="/{id}", method = RequestMethod.GET) //definindo que essa requisição será um request Get
    public ResponseEntity<?> find(@PathVariable Integer id){
        Cliente obj = service.buscar(id); //acessando o Service
        return ResponseEntity.ok().body(obj);
    }
}

package com.example.cursomc.resources;

import com.example.cursomc.domain.Pedido;
import com.example.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController //definindo que esta classe será um controlador Rest
@RequestMapping(value = "/pedidos") //definindo qual o endpoint padrão para essa classe
public class PedidoResource {
    @Autowired
    private PedidoService service; //com autoWired para ele instanciar automaticamente este obj

    @RequestMapping(value="/{id}", method = RequestMethod.GET) //definindo que essa requisição será um request Get
    public ResponseEntity<Pedido> find(@PathVariable Integer id){
        Pedido obj = service.find(id); //acessando o Service
        return ResponseEntity.ok().body(obj);
    }
}

package com.example.cursomc.services;

import com.example.cursomc.domain.Pedido;
import com.example.cursomc.repositories.PedidoRepository;
import com.example.cursomc.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired //esta dependencia será automaticamente instancianda pelo Spring
    private PedidoRepository repo;

    public Pedido find(Integer id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não foi encontrado! Id: " + id + " Tipo: " + Pedido.class.getName()
        ));
    }
}

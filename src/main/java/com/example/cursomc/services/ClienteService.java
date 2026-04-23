package com.example.cursomc.services;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired //esta dependencia será automaticamente instancianda pelo Spring
    private ClienteRepository repo;

    public Cliente buscar (Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não foi encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()
        ));
    }
}

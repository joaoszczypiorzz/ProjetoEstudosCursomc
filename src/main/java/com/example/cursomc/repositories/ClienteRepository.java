package com.example.cursomc.repositories;

import com.example.cursomc.domain.Cliente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Dizendo ao JPA que está classe é um repository, que busca os dados do BD
public interface ClienteRepository extends JpaRepository <Cliente, Integer> {

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}

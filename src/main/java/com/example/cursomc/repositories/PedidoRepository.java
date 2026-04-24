package com.example.cursomc.repositories;

import com.example.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Dizendo ao JPA que está classe é um repository, que busca os dados do BD
public interface PedidoRepository extends JpaRepository <Pedido, Integer> {

}

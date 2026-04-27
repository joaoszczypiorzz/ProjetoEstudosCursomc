package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.dto.CategoriaDto;
import com.example.cursomc.dto.ProdutoDto;
import com.example.cursomc.resources.utils.URL;
import com.example.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController //definindo que esta classe será um controlador Rest
@RequestMapping(value = "/produtos") //definindo qual o endpoint padrão para essa classe
public class ProdutoResource {
    @Autowired
    private ProdutoService service; //com autoWired para ele instanciar automaticamente este obj

    @RequestMapping(value="/{id}", method = RequestMethod.GET) //definindo que essa requisição será um request Get
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto obj = service.find(id); //acessando o Service
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDto>> findPage(
                                                     @RequestParam(value = "nome",defaultValue = "") String nome,
                                                     @RequestParam(value = "categorias",defaultValue = "") String categorias,
                                                     @RequestParam(value = "page",defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage,
                                                     @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction",defaultValue = "ASC") String direction){
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = service.serch(nomeDecoded,ids,page, linesPerPage, orderBy, direction);
        Page<ProdutoDto> listDto = list.map(obj -> new ProdutoDto(obj));
        return ResponseEntity.ok().body(listDto);
    }
}

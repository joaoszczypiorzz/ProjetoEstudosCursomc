package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDto;
import com.example.cursomc.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController //definindo que esta classe será um controlador Rest
@RequestMapping(value = "/categorias") //definindo qual o endpoint padrão para essa classe
public class CategoriaResource {
    @Autowired
    private CategoriaService service; //com autoWired para ele instanciar automaticamente este obj

    @RequestMapping(value="/{id}", method = RequestMethod.GET) //definindo que essa requisição será um request Get
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria obj = service.find(id); //acessando o Service
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List <CategoriaDto>> findAll(){
        List<Categoria> list = service.findAll();
        List<CategoriaDto> listDto = list.stream()
                .map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto objDto){
        Categoria obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDto objDto, @PathVariable Integer id){
        Categoria obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page" ,method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDto>> findPage(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage,
                                                       @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction",defaultValue = "ASC") String direction){
        Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDto> listDto = list.map(obj -> new CategoriaDto(obj));
        return ResponseEntity.ok().body(listDto);
    }
}

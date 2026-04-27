package com.example.cursomc.services;

import com.example.cursomc.Enums.TipoCliente;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.dto.ClienteDto;
import com.example.cursomc.dto.ClienteNewDto;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired //esta dependencia será automaticamente instancianda pelo Spring
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find (Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não foi encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()
        ));
    }

    @Transactional
    public Cliente insert(Cliente obj){
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir porque há entidades Relacionadas");
        }
    }

    public List<Cliente> findAll(){
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDto objDto){
        return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
    }

    public Cliente fromDto(ClienteNewDto objtDto){
        Cliente cli = new Cliente(null, objtDto.getNome(), objtDto.getEmail(),objtDto.getCpfOuCnpj(), TipoCliente.toEnum(objtDto.getTipo()));
        Cidade cid = new Cidade(objtDto.getCidadeId(),null,null);
        Endereco end = new Endereco(null, objtDto.getLogadouro(),objtDto.getNumero(),objtDto.getComplemento(),objtDto.getBairro(),objtDto.getCep(),cli,cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objtDto.getTelefone1());
        if(objtDto.getTelefone2() != null){
            cli.getTelefones().add(objtDto.getTelefone2());
        }
        if(objtDto.getTelefone3() != null){
            cli.getTelefones().add(objtDto.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

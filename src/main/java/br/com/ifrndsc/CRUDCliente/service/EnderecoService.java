package br.com.ifrndsc.CRUDCliente.service;

import br.com.ifrndsc.CRUDCliente.model.Cliente;
import br.com.ifrndsc.CRUDCliente.model.Endereco;
import br.com.ifrndsc.CRUDCliente.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    // Listar endereços
    public List<Endereco> findAll(){
        return enderecoRepository.findAll();
    }

    // Salvar no banco
    public Endereco save(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    // Atualizar no banco
    public Endereco update(Endereco endereco){
        return enderecoRepository.save(endereco);
    }


    // Deletar um endereco
    public void delete(Long id){
        enderecoRepository.deleteById(id);
    }

}

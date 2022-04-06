package br.com.ifrndsc.CRUDCliente.service;

import br.com.ifrndsc.CRUDCliente.model.Cliente;
import br.com.ifrndsc.CRUDCliente.model.Endereco;
import br.com.ifrndsc.CRUDCliente.repository.ClienteRepository;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoService enderecoService;

    // Listar clientes

    public List<Cliente> findAll(){
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return clienteRepository.findAll();
    }

    // Listar clientes por paginação
    public Page<Cliente> findAllPaginacao(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    // Listar todos os clientes a partir de um nome
    public List<Cliente> findByName(String nome){
        return clienteRepository.findByName(nome);
    }

    // Listar todos os clientes a partir de um nome
    public List<Cliente> findByName2(String nome){
        return clienteRepository.findByName2(nome);
    }

    // Retornar total de clientes
    public int totalCliente(){
        return clienteRepository.totalCliente();
    }

    @Cacheable(value = "cliente")
    // Retornar um cliente dado um id
    public Cliente findById(Long id){
        if(clienteRepository.findById(id).isPresent()){
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return clienteRepository.findById(id).get();
        }else{
            return null;
        }
    }

    // Salvar cliente
    public Cliente save(Cliente cliente){
        Endereco endecoSalvo = enderecoService.save(cliente.getEndereco());
        cliente.setEndereco(endecoSalvo);
        return clienteRepository.save(cliente);
    }

    // Atualizar um cliente
    public Cliente update(Cliente cliente){
        Endereco enderecoAtualizado = enderecoService.save(cliente.getEndereco());
        cliente.setEndereco(enderecoAtualizado);
        return clienteRepository.save(cliente);
    }

    // Deletar um cliente
    public void delete(Long id){
        clienteRepository.deleteById(id);
        enderecoService.delete(id);
    }


}
